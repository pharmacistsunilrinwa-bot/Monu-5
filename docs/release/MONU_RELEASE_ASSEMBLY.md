# MONU RELEASE ASSEMBLY

## Target

Real production Android APK.

This is not a demonstration APK and not a testing-only APK.

## Build Environment

GitHub Actions.

Termux is used only for source development and Git operations.

## AI Configuration

The production build receives:

MONU_GEMINI_API_KEY

from GitHub Secrets.

The APK supports direct Gemini connectivity.

MONU Server remains optional for future expansion.

## Required Before Final Build

1. GitHub repository remote configured.
2. Source pushed manually by owner.
3. MONU_GEMINI_API_KEY added to GitHub Secrets.
4. Release signing configuration finalized.
5. GitHub Actions workflow triggered.

## Expected Output

A real installable signed Android APK.

Distribution:

Private personal use.
