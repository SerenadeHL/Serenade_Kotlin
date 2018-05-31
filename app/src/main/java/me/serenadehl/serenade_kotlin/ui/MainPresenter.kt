package me.serenadehl.serenade_kotlin.ui

import me.serenadehl.serenade_kotlin.base.mvpbase.MVPBasePresenter
import me.serenadehl.serenade_kotlin.extensions.async
import me.serenadehl.serenade_kotlin.extensions.log
import me.serenadehl.serenade_kotlin.retrofit.BaseObserverWithoutBaseResponse
import okhttp3.ResponseBody

class MainPresenter : MVPBasePresenter<MainActivity, MainModel>(), IMainPresenter {
    override fun createModel() = MainModel()

    override fun loginByPhone(phone: String, password: String) {
        getModel().loginByPhone(phone, password)
                .async()
                .subscribe(object : BaseObserverWithoutBaseResponse<ResponseBody>() {
                    override fun next(data: ResponseBody) {
                        data.string().log()
                    }
                })
    }
}
