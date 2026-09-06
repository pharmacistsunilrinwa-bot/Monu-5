# MONU GitHub Secrets

Configure these repository secrets before building the production APK.

## Required

MONU_GEMINI_API_KEY

Value:
Your Google Gemini API key.

## Optional

MONU_SERVER_URL

Value:
Your MONU backend server URL.

Example:
https://your-server.example.com

## Build flow

GitHub Repository Secret
        ↓
GitHub Actions Environment Variable
        ↓
Gradle Build Configuration
        ↓
BuildConfig
        ↓
MONU APK Runtime

Never commit the real API key into:
- Kotlin files
- JSON files
- XML resources
- local.properties.example
- README
- Git history
