package me.serenadehl.serenade_kotlin.bean



data class User(
    val loginType: Int,
    val code: Int,
    val account: Account,
    val profile: Profile,
    val bindings: List<Binding>
)