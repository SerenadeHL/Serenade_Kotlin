package me.serenadehl.serenade_kotlin

import me.serenadehl.serenade_kotlin.base.BaseActivity
import me.serenadehl.serenade_kotlin.extensions.async
import me.serenadehl.serenade_kotlin.retrofit.BaseObserverWithoutBaseResponse
import me.serenadehl.serenade_kotlin.retrofit.Request
import me.serenadehl.serenade_kotlin.utils.log.L
import okhttp3.ResponseBody

class MainActivity : BaseActivity() {
    override fun layout() = R.layout.activity_main

    override fun onActivityCreated() {
        Request.loginByPhone("17600697395", "Haoliang0423")
                .async()
                .subscribe(object : BaseObserverWithoutBaseResponse<ResponseBody>() {
                    override fun next(data: ResponseBody) {
                        L.e(data.string())
                    }
                })
    }
}
