#!/data/data/com.termux/files/usr/bin/bash

cd "$HOME/MONU" || exit 1

source .venv/bin/activate

uvicorn server.app.main:app \
--host 0.0.0.0 \
--port 8000
