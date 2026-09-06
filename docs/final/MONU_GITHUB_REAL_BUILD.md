# MONU REAL APK BUILD

## Build Location

The real Android build is performed on GitHub Actions.

Termux is not required to compile the final APK.

## Required GitHub Secret

MONU_GEMINI_API_KEY

Optional:

MONU_SERVER_URL

## Build Flow

GitHub Repository
↓
GitHub Actions
↓
Java 17
↓
Android SDK
↓
Gradle
↓
assembleDebug
↓
assembleRelease
↓
APK Artifacts

## Important

The first real build is expected to reveal actual compilation
or dependency compatibility issues, if any.

Those issues must be fixed from the GitHub Actions build logs.

Architecture completion does not replace compilation.

## Target

A real installable MONU APK.
