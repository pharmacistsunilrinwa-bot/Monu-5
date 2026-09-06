# MONU AI ARCHITECTURE

Owner: Sunil Rinwa
AI Identity: MONU

## Central Architecture

MONU is controlled through a Central AI Brain.

Central AI Brain coordinates:

- Chat Engine
- Memory Engine
- Tool Router
- Model Router
- Server Connection
- Media Engine
- Voice Engine
- Health Monitor
- Dashboard

## Quad Routing

Every supported request may be routed to:

1. MONU Server
2. Local SQLite / Room Database
3. Google GenAI Provider
4. Wikipedia Provider

Routing is policy-based and asynchronous.

## Security Principle

Secrets are not trusted inside a distributable APK.
Production API credentials should remain server-side.

