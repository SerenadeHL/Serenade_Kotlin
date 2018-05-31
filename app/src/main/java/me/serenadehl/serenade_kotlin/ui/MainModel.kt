package me.serenadehl.serenade_kotlin.ui

import io.reactivex.Observable
import me.serenadehl.serenade_kotlin.base.mvpbase.MVPBaseModel
import me.serenadehl.serenade_kotlin.retrofit.Requests
import okhttp3.ResponseBody

class MainModel : MVPBaseModel(), IMainModel {
    override fun loginByPhone(phone: String, password: String) = Requests.loginByPhone(phone, password)

}