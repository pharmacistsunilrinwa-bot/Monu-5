# MONU Phase 20 - Persistent Storage Integration

## APK Persistence Layer

Central persistence architecture:

Application
    ↓
MonuStorageProvider
    ↓
MonuPersistenceManager
    ↓
MonuPreferences
    ↓
Android SharedPreferences

## Persisted State

- Selected AI model
- Server URL
- Conversation draft
- Conversation session
- Last navigation route
- Voice state
- Privacy mode
- Temporary chat mode
- Onboarding state

## Design

The persistence layer is APK-local and survives
normal application process restarts.

Large conversation and message data remain handled
by the dedicated MONU database architecture.
