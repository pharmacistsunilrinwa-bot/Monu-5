package com.monu.ai.appfeatures

data class MonuOwnerProfile(
    val ownerName: String = "Sunil Rinwa",
    val aiName: String = "MONU",
    val avatarUri: String? = null,
    val preferredLanguage: String = "hi",
    val createdAt: Long = System.currentTimeMillis()
)

class MonuProfileController {

    private var profile = MonuOwnerProfile()

    fun get(): MonuOwnerProfile = profile

    fun update(
        updated: MonuOwnerProfile
    ) {
        profile = updated
    }
}
