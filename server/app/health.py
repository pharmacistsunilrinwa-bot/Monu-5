import time
import requests

from .database import SessionLocal, HealthLog


def log_health(
    component: str,
    status: str,
    latency_ms=None,
    message=None
):
    db = SessionLocal()

    try:
        row = HealthLog(
            component=component,
            status=status,
            latency_ms=latency_ms,
            message=message
        )

        db.add(row)
        db.commit()

    finally:
        db.close()


def check_url(name: str, url: str):

    try:
        start = time.perf_counter()

        response = requests.get(
            url,
            timeout=10
        )

        latency = round(
            (time.perf_counter() - start) * 1000
        )

        status = "connected" if response.ok else "error"

        log_health(
            name,
            status,
            latency,
            f"HTTP {response.status_code}"
        )

        return {
            "component": name,
            "status": status,
            "latency_ms": latency,
            "message": f"HTTP {response.status_code}"
        }

    except Exception as exc:

        log_health(
            name,
            "error",
            None,
            str(exc)
        )

        return {
            "component": name,
            "status": "error",
            "message": str(exc)
        }
