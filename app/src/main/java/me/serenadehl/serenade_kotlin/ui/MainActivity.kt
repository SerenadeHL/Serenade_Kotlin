package me.serenadehl.serenade_kotlin.ui

import me.serenadehl.serenade_kotlin.R
import me.serenadehl.serenade_kotlin.base.BaseActivity
import me.serenadehl.serenade_kotlin.extensions.async
import me.serenadehl.serenade_kotlin.extensions.log
import me.serenadehl.serenade_kotlin.retrofit.BaseObserverWithoutBaseResponse
import me.serenadehl.serenade_kotlin.retrofit.Request
import okhttp3.ResponseBody

class MainActivity : BaseActivity() {
    override fun layout() = R.layout.activity_main

    override fun onActivityCreated() {
        Request.loginByPhone("17600697395", "Haoliang0423")
                .async()
                .subscribe(object : BaseObserverWithoutBaseResponse<ResponseBody>() {
                    override fun next(data: ResponseBody) {
                        data.string().log()
                    }
                })
    }
}
