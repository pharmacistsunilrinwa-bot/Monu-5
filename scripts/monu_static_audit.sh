#!/data/data/com.termux/files/usr/bin/bash

set +e

cd "$HOME/MONU" || exit 1

echo "=========================================="
echo " MONU STATIC PROJECT INTEGRITY AUDIT"
echo "=========================================="

PASS=0
FAIL=0

check_file() {
    if [ -f "$1" ]; then
        echo "[PASS] $1"
        PASS=$((PASS+1))
    else
        echo "[FAIL] $1"
        FAIL=$((FAIL+1))
    fi
}

check_dir() {
    if [ -d "$1" ]; then
        echo "[PASS] $1/"
        PASS=$((PASS+1))
    else
        echo "[FAIL] $1/"
        FAIL=$((FAIL+1))
    fi
}

echo
echo "--- CORE PROJECT ---"

check_file "android/settings.gradle.kts"
check_file "android/build.gradle.kts"
check_file "android/app/build.gradle.kts"
check_file "android/app/src/main/AndroidManifest.xml"

echo
echo "--- APPLICATION CORE ---"

check_file "android/app/src/main/java/com/monu/ai/MainActivity.kt"
check_file "android/app/src/main/java/com/monu/ai/MonuApplication.kt"
check_file "android/app/src/main/java/com/monu/ai/MonuBrain.kt"
check_file "android/app/src/main/java/com/monu/ai/MonuRuntimeController.kt"

echo
echo "--- AI RUNTIME ---"

check_dir "android/app/src/main/java/com/monu/ai/brain"
check_dir "android/app/src/main/java/com/monu/ai/network"
check_dir "android/app/src/main/java/com/monu/ai/chat"
check_dir "android/app/src/main/java/com/monu/ai/memory"

echo
echo "--- APK FEATURES ---"

check_dir "android/app/src/main/java/com/monu/ai/voice"
check_dir "android/app/src/main/java/com/monu/ai/media"
check_dir "android/app/src/main/java/com/monu/ai/navigation"
check_dir "android/app/src/main/java/com/monu/ai/settings"
check_dir "android/app/src/main/java/com/monu/ai/security"

echo
echo "--- FINAL SYSTEMS ---"

check_dir "android/app/src/main/java/com/monu/ai/runtime"
check_dir "android/app/src/main/java/com/monu/ai/sync"
check_dir "android/app/src/main/java/com/monu/ai/integrity"

echo
echo "--- GITHUB BUILD ---"

check_file ".github/workflows/build-apk.yml"

echo
echo "=========================================="
echo " PASS: $PASS"
echo " FAIL: $FAIL"
echo "=========================================="

mkdir -p docs/production

cat > docs/production/MONU_STATIC_AUDIT_LAST.txt <<REPORT
MONU STATIC PROJECT AUDIT

PASS=$PASS
FAIL=$FAIL

Audit type:
Static file and architecture presence check.

No APK build performed.
No pip dependency installation performed.
No server testing performed.
No GitHub push performed.
REPORT

echo
echo "Static audit report saved:"
echo "docs/production/MONU_STATIC_AUDIT_LAST.txt"

exit 0
