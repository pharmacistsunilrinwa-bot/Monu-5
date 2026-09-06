# MONU Complete APK Navigation Architecture

                     MONU APPLICATION
                            │
                     Central Navigator
                            │
        ┌───────────────────┼───────────────────┐
        │                   │                   │
       CHAT              DRAWER              PLUS MENU
        │                   │                   │
        │              ┌────┴────┐         ┌────┴─────┐
        │              │         │         │          │
    Dashboard       Settings  Library   Media     Notebooks
        │
        ├── Connection
        ├── Model Selector
        ├── Quad Results
        │
        ├── Voice
        ├── Profile
        ├── Search
        ├── Favorites
        ├── Archive
        ├── Privacy
        └── Security

All navigation flows through:

MonuAppNavigator

Deep links are resolved through:

MonuDeepLinkResolver

Drawer and Plus Menu destinations are mapped
to the same central route system.

This prevents isolated screens and creates
one unified APK navigation architecture.
