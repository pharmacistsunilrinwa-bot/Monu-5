import time
import requests
from datetime import datetime, timezone

from .config import MONU_NAME, MONU_OWNER, WIKIPEDIA_API
from .database import SessionLocal, Message, Memory


class MonuBrain:

    def __init__(self):
        self.name = MONU_NAME
        self.owner = MONU_OWNER

    def analyze(self, text: str) -> dict:
        text = (text or "").strip()

        if not text:
            return {
                "intent": "empty",
                "routes": ["local_memory"],
                "message": "Please provide a message."
            }

        lower = text.lower()

        if lower.startswith("remember ") or "याद रख" in lower:
            intent = "memory_write"
        elif lower.startswith("search ") or "विकिपीडिया" in lower or "wikipedia" in lower:
            intent = "knowledge_search"
        else:
            intent = "conversation"

        return {
            "intent": intent,
            "routes": [
                "monu_server",
                "local_memory",
                "gemini",
                "wikipedia"
            ]
        }


class QuadRouter:

    def __init__(self):
        self.brain = MonuBrain()

    def local_memory(self, conversation_id: int, message: str):
        db = SessionLocal()
        try:
            memories = db.query(Memory).order_by(Memory.updated_at.desc()).limit(5).all()

            return {
                "status": "ok",
                "count": len(memories),
                "recent_memory": [
                    {
                        "key": m.memory_key,
                        "value": m.memory_value
                    }
                    for m in memories
                ]
            }
        finally:
            db.close()

    def wikipedia(self, message: str):
        query = message.replace("search ", "").strip()

        if not query:
            return {
                "status": "skipped",
                "reason": "No search query"
            }

        try:
            start = time.perf_counter()

            response = requests.get(
                f"{WIKIPEDIA_API}/{requests.utils.quote(query)}",
                timeout=8
            )

            latency = round((time.perf_counter() - start) * 1000)

            if response.status_code == 200:
                data = response.json()

                return {
                    "status": "ok",
                    "latency_ms": latency,
                    "title": data.get("title"),
                    "summary": data.get("extract")
                }

            return {
                "status": "not_found",
                "latency_ms": latency
            }

        except Exception as exc:
            return {
                "status": "error",
                "error": str(exc)
            }

    def gemini(self, message: str):
        # Provider interface ready.
        # API key integration will be added through the secure Android
        # fallback/build pipeline rather than hardcoding a key here.
        return {
            "status": "not_configured",
            "message": "Gemini provider awaiting secure configuration"
        }

    def server(self, message: str):
        return {
            "status": "ok",
            "engine": "MONU_LOCAL_SERVER",
            "processed_at": datetime.now(timezone.utc).isoformat()
        }

    def process(
        self,
        conversation_id: int,
        message: str
    ):
        analysis = self.brain.analyze(message)

        results = {}

        results["monu_server"] = self.server(message)

        results["local_memory"] = self.local_memory(
            conversation_id,
            message
        )

        if analysis["intent"] == "knowledge_search":
            results["wikipedia"] = self.wikipedia(message)
        else:
            results["wikipedia"] = {
                "status": "available"
            }

        results["gemini"] = self.gemini(message)

        return analysis, results
