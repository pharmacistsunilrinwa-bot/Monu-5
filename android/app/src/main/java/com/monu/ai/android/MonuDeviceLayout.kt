package com.monu.ai.android

enum class MonuWindowClass {
    COMPACT,
    MEDIUM,
    EXPANDED
}

enum class MonuLayoutMode {
    PHONE,
    TABLET,
    FOLDABLE
}

data class MonuDeviceLayoutState(
    val windowClass: MonuWindowClass,
    val layoutMode: MonuLayoutMode,
    val supportsTwoPane: Boolean
)

object MonuDeviceLayout {

    fun calculate(
        widthDp: Int,
        isFoldable: Boolean = false
    ): MonuDeviceLayoutState {

        val windowClass = when {
            widthDp < 600 -> MonuWindowClass.COMPACT
            widthDp < 840 -> MonuWindowClass.MEDIUM
            else -> MonuWindowClass.EXPANDED
        }

        val mode = when {
            isFoldable -> MonuLayoutMode.FOLDABLE
            widthDp >= 600 -> MonuLayoutMode.TABLET
            else -> MonuLayoutMode.PHONE
        }

        return MonuDeviceLayoutState(
            windowClass = windowClass,
            layoutMode = mode,
            supportsTwoPane = windowClass != MonuWindowClass.COMPACT
        )
    }
}
