package me.serenadehl.serenade_kotlin.ui

import io.reactivex.Observable
import me.serenadehl.serenade_kotlin.base.mvpbase.IBaseModel
import me.serenadehl.serenade_kotlin.base.mvpbase.IBasePresenter
import me.serenadehl.serenade_kotlin.base.mvpbase.IBaseView
import me.serenadehl.serenade_kotlin.retrofit.BaseObserverWithoutBaseResponse
import okhttp3.ResponseBody
import java.util.*

interface IMainView : IBaseView

interface IMainPresenter : IBasePresenter {
    fun loginByPhone(phone: String, password: String)
}

interface IMainModel : IBaseModel {
    fun loginByPhone(phone: String, password: String): Observable<ResponseBody>
}
