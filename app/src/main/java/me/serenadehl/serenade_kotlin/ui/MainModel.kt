package me.serenadehl.serenade_kotlin.ui

import me.serenadehl.serenade_kotlin.base.mvpbase.MVPBaseModel
import me.serenadehl.serenade_kotlin.retrofit.Requests

class MainModel : MVPBaseModel(), IMainModel {
    override fun loginByPhone(phone: String, password: String) = Requests.loginByPhone(phone, password)

}