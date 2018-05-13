package me.serenadehl.serenade_kotlin.bean


data class Binding(
    val tokenJsonStr: String,
    val expiresIn: Int,
    val refreshTime: Int,
    val url: String,
    val userId: Int,
    val expired: Boolean,
    val id: Long,
    val type: Int
)