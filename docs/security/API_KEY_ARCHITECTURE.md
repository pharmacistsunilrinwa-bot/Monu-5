# MONU API KEY ARCHITECTURE

MONU supports two AI connectivity paths:

1. Primary:
   MONU APK -> MONU Server -> AI Provider

2. Offline Server Fallback:
   MONU APK -> Direct AI Provider

Security rules:

- Never store API keys in source files.
- Never commit real keys to Git.
- Never place real keys in strings.xml.
- Never use a plaintext .env inside the distributable APK.
- Release builds use code/resource shrinking and obfuscation.
- Local encrypted configuration is treated as a defense-in-depth layer,
  not as mathematically perfect secrecy.
- Keys should have API restrictions and rotation capability.

The AI Router decides whether server routing or direct fallback is active.
