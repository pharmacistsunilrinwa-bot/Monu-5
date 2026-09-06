from pathlib import Path
import os

ROOT = Path(__file__).resolve().parent.parent
DATA_DIR = ROOT / "data"
DATA_DIR.mkdir(parents=True, exist_ok=True)

DATABASE_URL = os.getenv(
    "MONU_DATABASE_URL",
    f"sqlite:///{DATA_DIR / 'monu.db'}"
)

MONU_OWNER = "Sunil Rinwa"
MONU_NAME = "MONU"

SERVER_HOST = os.getenv("MONU_HOST", "0.0.0.0")
SERVER_PORT = int(os.getenv("MONU_PORT", "8000"))

WIKIPEDIA_API = "https://en.wikipedia.org/api/rest_v1/page/summary"

HEALTH_INTERVAL_SECONDS = 300
