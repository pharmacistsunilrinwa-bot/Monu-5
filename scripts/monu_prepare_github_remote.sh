#!/data/data/com.termux/files/usr/bin/bash

cd "$HOME/MONU" || exit 1

REMOTE_URL="https://github.com/pharmacistsunilrinwa-bot/Monu-5.git"

echo "=========================================="
echo " MONU GITHUB REMOTE PREPARATION"
echo "=========================================="

if git remote get-url origin >/dev/null 2>&1; then
    echo "Existing origin:"
    git remote get-url origin
    echo
    echo "No changes made."
else
    git remote add origin "$REMOTE_URL"
    echo "GitHub origin configured:"
    git remote -v
fi

echo
echo "IMPORTANT:"
echo "This command DOES NOT push anything."
echo "Push will only happen after explicit owner instruction."
