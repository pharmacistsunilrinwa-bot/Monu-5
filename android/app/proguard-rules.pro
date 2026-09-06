-keep class androidx.room.** { *; }
-keep class * extends androidx.room.RoomDatabase
-keepattributes Signature
-keepattributes *Annotation*

# MONU network models
-keep class com.monu.ai.** { *; }

# Keep entry activity
-keep class com.monu.ai.MainActivity { *; }
