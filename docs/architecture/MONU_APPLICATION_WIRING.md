# MONU Unified Application Wiring

                    MONU APPLICATION
                           │
                           ▼
                 MonuApplicationWiring
                           │
          ┌────────────────┼────────────────┐
          │                │                │
          ▼                ▼                ▼
      Dispatcher       Coordinator       Runtime
          │                │                │
          ▼                ▼                ▼
      App Events       App State      Runtime Bridge
          │                                 │
          │                                 ▼
          │                           AI Runtime
          │                                 │
          └───────────────┬─────────────────┘
                          │
                          ▼
                    Provider Layer
                          │
             ┌────────────┼────────────┐
             ▼            ▼            ▼
           Gemini       Server      Fallback
             │            │            │
             └────────────┼────────────┘
                          ▼
                    Response Flow
                          │
                          ▼
                     Chat UI

Connected application modules:

- Main Application
- Central AI Brain
- Navigation
- Chat UI
- Runtime AI
- Provider Registry
- Gemini Gateway
- Memory
- Media
- Voice
- Response Actions
- Connectivity
- Offline Sync
- Dashboard

This wiring layer is the central bridge that prevents
MONU from operating as isolated feature files.
