# MONU REAL APK RELEASE CHECKLIST

## Source
- [x] Central AI Brain architecture
- [x] Direct AI provider architecture
- [x] Local memory architecture
- [x] Voice architecture
- [x] Media architecture
- [x] Runtime wiring
- [x] Navigation architecture
- [x] Offline sync architecture

## Production
- [x] Production configuration
- [x] Network security configuration
- [x] Backup rules
- [x] FileProvider configuration
- [x] Crash architecture
- [x] Release contract

## Before GitHub Build
- [ ] Verify Gradle dependency compatibility
- [ ] Verify Android SDK / compileSdk versions
- [ ] Configure signing strategy
- [ ] Add GitHub Secrets
- [ ] Push source to private repository

## GitHub Secrets
- MONU_GEMINI_API_KEY
- MONU_SERVER_URL (optional)

## Build
- Generate production APK
- Download release artifact
- Install on owner device
