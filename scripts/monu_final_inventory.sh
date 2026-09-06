#!/data/data/com.termux/files/usr/bin/bash

cd "$HOME/MONU" || exit 1

echo "=========================================="
echo " MONU FINAL SOURCE INVENTORY"
echo "=========================================="

echo
echo "--- KOTLIN SOURCE FILE COUNT ---"
find android/app/src/main/java -type f -name "*.kt" | wc -l

echo
echo "--- ANDROID RESOURCE FILE COUNT ---"
find android/app/src/main/res -type f | wc -l

echo
echo "--- DOCUMENTATION FILE COUNT ---"
find docs -type f | wc -l

echo
echo "--- PROJECT TOP LEVEL ---"
find . -maxdepth 1 -mindepth 1 \
  ! -name ".git" \
  ! -name ".venv" \
  -printf "%f\n" | sort

echo
echo "--- CORE MODULE DIRECTORIES ---"
for dir in \
  brain \
  network \
  chat \
  voice \
  media \
  navigation \
  security \
  runtime \
  sync \
  integrity \
  finalization
do
  if [ -d "android/app/src/main/java/com/monu/ai/$dir" ]; then
    echo "[READY] $dir"
  else
    echo "[MISSING] $dir"
  fi
done

echo
echo "=========================================="
echo " INVENTORY COMPLETE"
echo "=========================================="
