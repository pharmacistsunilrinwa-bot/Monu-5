from contextlib import asynccontextmanager
from datetime import datetime, timezone

from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, Field

from .config import MONU_NAME, MONU_OWNER
from .database import (
    init_database,
    SessionLocal,
    Conversation,
    Message,
    Memory
)
from .brain import QuadRouter


@asynccontextmanager
async def lifespan(app: FastAPI):
    init_database()
    yield


app = FastAPI(
    title="MONU AI SERVER",
    version="1.0.0",
    lifespan=lifespan
)

router = QuadRouter()


class ChatRequest(BaseModel):
    conversation_id: int | None = None
    message: str = Field(min_length=1)


class RenameRequest(BaseModel):
    title: str = Field(min_length=1, max_length=300)


class MemoryRequest(BaseModel):
    key: str = Field(min_length=1, max_length=300)
    value: str = Field(min_length=1)


@app.get("/")
def root():
    return {
        "name": MONU_NAME,
        "owner": MONU_OWNER,
        "status": "online"
    }


@app.get("/health")
def health():
    return {
        "status": "healthy",
        "service": "MONU SERVER",
        "timestamp": datetime.now(timezone.utc).isoformat()
    }


@app.post("/chat")
def chat(request: ChatRequest):

    db = SessionLocal()

    try:

        if request.conversation_id is None:

            conversation = Conversation(
                title=request.message[:60]
            )

            db.add(conversation)
            db.commit()
            db.refresh(conversation)

        else:

            conversation = db.get(
                Conversation,
                request.conversation_id
            )

            if not conversation:
                raise HTTPException(
                    status_code=404,
                    detail="Conversation not found"
                )

        user_message = Message(
            conversation_id=conversation.id,
            role="user",
            content=request.message,
            route="quad_router"
        )

        db.add(user_message)
        db.commit()

        analysis, routes = router.process(
            conversation.id,
            request.message
        )

        response_text = (
            f"{MONU_NAME}: Request processed. "
            f"Intent={analysis['intent']}"
        )

        assistant_message = Message(
            conversation_id=conversation.id,
            role="assistant",
            content=response_text,
            route="central_brain"
        )

        db.add(assistant_message)
        db.commit()

        return {
            "conversation_id": conversation.id,
            "response": response_text,
            "brain": analysis,
            "quad_routes": routes
        }

    finally:
        db.close()


@app.get("/conversations")
def conversations():

    db = SessionLocal()

    try:

        rows = (
            db.query(Conversation)
            .order_by(
                Conversation.pinned.desc(),
                Conversation.updated_at.desc()
            )
            .all()
        )

        return [
            {
                "id": row.id,
                "title": row.title,
                "pinned": row.pinned,
                "created_at": row.created_at
            }
            for row in rows
        ]

    finally:
        db.close()


@app.post("/conversations/{conversation_id}/pin")
def toggle_pin(conversation_id: int):

    db = SessionLocal()

    try:

        row = db.get(
            Conversation,
            conversation_id
        )

        if not row:
            raise HTTPException(
                status_code=404,
                detail="Conversation not found"
            )

        row.pinned = not row.pinned

        db.commit()

        return {
            "id": row.id,
            "pinned": row.pinned
        }

    finally:
        db.close()


@app.post("/conversations/{conversation_id}/rename")
def rename_conversation(
    conversation_id: int,
    request: RenameRequest
):

    db = SessionLocal()

    try:

        row = db.get(
            Conversation,
            conversation_id
        )

        if not row:
            raise HTTPException(
                status_code=404,
                detail="Conversation not found"
            )

        row.title = request.title
        db.commit()

        return {
            "id": row.id,
            "title": row.title
        }

    finally:
        db.close()


@app.delete("/conversations/{conversation_id}")
def delete_conversation(conversation_id: int):

    db = SessionLocal()

    try:

        row = db.get(
            Conversation,
            conversation_id
        )

        if not row:
            raise HTTPException(
                status_code=404,
                detail="Conversation not found"
            )

        db.delete(row)

        db.query(Message).filter(
            Message.conversation_id == conversation_id
        ).delete()

        db.commit()

        return {
            "deleted": True,
            "conversation_id": conversation_id
        }

    finally:
        db.close()


@app.post("/memory")
def write_memory(request: MemoryRequest):

    db = SessionLocal()

    try:

        row = (
            db.query(Memory)
            .filter(
                Memory.memory_key == request.key
            )
            .first()
        )

        if row:
            row.memory_value = request.value
        else:
            row = Memory(
                memory_key=request.key,
                memory_value=request.value
            )

            db.add(row)

        db.commit()

        return {
            "status": "stored",
            "key": request.key
        }

    finally:
        db.close()


@app.get("/memory")
def read_memory():

    db = SessionLocal()

    try:

        rows = (
            db.query(Memory)
            .order_by(Memory.updated_at.desc())
            .all()
        )

        return [
            {
                "key": row.memory_key,
                "value": row.memory_value
            }
            for row in rows
        ]

    finally:
        db.close()


@app.get("/diagnostics")
def diagnostics():

    return {
        "monu_server": "running",
        "database": "configured",
        "central_ai_brain": "active",
        "quad_router": {
            "monu_server": "active",
            "local_memory": "active",
            "gemini": "awaiting_secure_config",
            "wikipedia": "active"
        }
    }
