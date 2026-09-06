# MONU Runtime AI Engine

The runtime engine is the execution bridge between:

User Input
    ↓
Chat UI
    ↓
Central AI Brain
    ↓
Memory Context
    ↓
Route Selection
    ↓
AI Runtime
    ↓
Provider Registry
    ↓
Gemini / MONU Server / Fallback
    ↓
Streaming Response
    ↓
Chat UI

Core responsibilities:

- Request lifecycle tracking
- Provider selection
- Runtime fallback
- Cancellation support
- Streaming callback support
- Error isolation
- Central execution boundary

This layer intentionally contains no real API key.
The release APK receives secure configuration during the GitHub build pipeline.
