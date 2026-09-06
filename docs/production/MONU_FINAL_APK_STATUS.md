# MONU FINAL APK STATUS

## Product
MONU Personal AI Android APK

## AI Connectivity

Primary local application capability:

MONU APK
→ Central AI Runtime
→ Gemini Direct Provider
→ Google Gemini API

Optional future architecture:

MONU APK
→ MONU Server
→ Multiple AI Providers
→ Advanced Cloud Intelligence

The APK must remain capable of direct AI provider operation
without requiring the future MONU server.

## Core APK Features

- AI chat
- Direct Gemini provider
- Optional server routing
- Streaming architecture
- Stop generation
- Retry generation
- Edit and resend
- Response variants
- Copy response
- Voice response
- Share response
- Regenerate response
- Conversation memory
- Search
- Pin chats
- Rename chats
- Delete chats
- Notebooks
- Attachments
- File picker
- Photo picker
- Camera architecture
- Voice input
- Voice output
- Offline architecture
- Backup
- Import/export
- Privacy architecture
- Biometric architecture
- Android integration
- Deep links
- Dynamic themes
- Tablet/foldable architecture

## API Key Build Strategy

The real Gemini API key is never committed into Git.

GitHub Actions receives:

MONU_GEMINI_API_KEY

from GitHub Secrets during the production build.

The generated APK receives the configured build value.

## Build Policy

- No Termux heavy production build required
- No Termux dependency compilation required
- GitHub Actions performs the actual Android build
- Target is a real installable APK
- No automatic GitHub push
- Repository push remains manual

## Future Server

MONU Server is an optional future intelligence expansion.

The APK is designed to function with direct Gemini connectivity
before the server is deployed.
