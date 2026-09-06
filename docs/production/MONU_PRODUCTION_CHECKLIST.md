# MONU AI Production Checklist

## Core

- [x] Central AI Brain
- [x] Execution Planner
- [x] Quad Routing
- [x] Local Memory
- [x] Conversation Management
- [x] Media Architecture
- [x] Voice Architecture
- [x] Connection Dashboard
- [x] Health Diagnostics
- [x] Crash Logging

## AI Providers

- [x] Gemini Provider Layer
- [x] Model Registry
- [x] API Gateway Architecture
- [x] Secure BuildConfig Injection
- [x] GitHub Secrets Pipeline

## Android

- [x] Application Class
- [x] Runtime Controller
- [x] Foreground Service Architecture
- [x] FileProvider
- [x] Permission Architecture
- [x] Central Navigation Registry

## Before GitHub Build

- [ ] Add MONU_GEMINI_API_KEY GitHub Secret
- [ ] Add MONU_SERVER_URL GitHub Secret if server is used
- [ ] Verify Gradle wrapper availability
- [ ] Verify Android SDK compatibility in GitHub Actions
- [ ] Run GitHub Actions build
- [ ] Inspect generated APK
- [ ] Install APK on owner device
- [ ] Grant required runtime permissions
- [ ] Perform final functional validation
