package me.serenadehl.serenade_kotlin.bean


data class Account(
    val id: Int,
    val userName: String,
    val type: Int,
    val status: Int,
    val whitelistAuthority: Int,
    val createTime: Long,
    val salt: String,
    val tokenVersion: Int,
    val ban: Int,
    val baoyueVersion: Int,
    val donateVersion: Int,
    val vipType: Int,
    val viptypeVersion: Int,
    val anonimousUser: Boolean
)