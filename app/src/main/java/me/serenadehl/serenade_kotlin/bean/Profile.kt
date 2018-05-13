package me.serenadehl.serenade_kotlin.bean


data class Profile(
    val nickname: String,
    val remarkName: Any,
    val mutual: Boolean,
    val avatarImgId: Long,
    val province: Int,
    val defaultAvatar: Boolean,
    val avatarUrl: String,
    val birthday: Long,
    val city: Int,
    val backgroundImgId: Long,
    val userType: Int,
    val expertTags: Any,
    val authStatus: Int,
    val avatarImgIdStr: String,
    val backgroundImgIdStr: String,
    val backgroundUrl: String,
    val vipType: Int,
    val detailDescription: String,
    val djStatus: Int,
    val followed: Boolean,
    val userId: Int,
    val gender: Int,
    val description: String,
    val accountStatus: Int,
    val signature: String,
    val authority: Int
)