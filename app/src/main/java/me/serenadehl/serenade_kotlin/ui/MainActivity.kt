package me.serenadehl.serenade_kotlin.ui

import me.serenadehl.serenade_kotlin.R
import me.serenadehl.serenade_kotlin.base.mvpbase.*
import me.serenadehl.serenade_kotlin.extensions.async
import me.serenadehl.serenade_kotlin.extensions.log
import me.serenadehl.serenade_kotlin.retrofit.BaseObserverWithoutBaseResponse
import me.serenadehl.serenade_kotlin.retrofit.Requests
import okhttp3.ResponseBody

class MainActivity : MVPBaseActivity<MainPresenter>(), IMainView {
    override fun createPresenter() = MainPresenter()

    override fun layout() = R.layout.activity_main

    override fun onActivityCreated() {
//        mPresenter.loginByPhone("17600697395", "Haoliang0423")
        Requests.sendText("testhhhh", 127677408.toString())//475625142  32953014
                .async()
                .subscribe(object : BaseObserverWithoutBaseResponse<ResponseBody>() {
                    override fun next(data: ResponseBody) {
                        data.string().log()
                    }
                })
    }
}
