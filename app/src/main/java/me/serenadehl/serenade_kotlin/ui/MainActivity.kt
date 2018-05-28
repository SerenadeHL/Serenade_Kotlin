package me.serenadehl.serenade_kotlin.ui

import kotlinx.android.synthetic.main.activity_main.*
import me.serenadehl.serenade_kotlin.R
import me.serenadehl.serenade_kotlin.base.BaseActivity
import me.serenadehl.serenade_kotlin.base.mvpbase.IBaseModel
import me.serenadehl.serenade_kotlin.base.mvpbase.IBaseView
import me.serenadehl.serenade_kotlin.base.mvpbase.MVPBaseActivity
import me.serenadehl.serenade_kotlin.base.mvpbase.MVPBaseModel
import me.serenadehl.serenade_kotlin.extensions.async
import me.serenadehl.serenade_kotlin.extensions.log
import me.serenadehl.serenade_kotlin.retrofit.BaseObserverWithoutBaseResponse
import me.serenadehl.serenade_kotlin.retrofit.Requests
import okhttp3.ResponseBody


class MainActivity : MVPBaseActivity<MainPresenter>(),IMainView {
    override fun createPresenter() = MainPresenter()

    override fun layout() = R.layout.activity_main

    override fun onActivityCreated() {
        Requests.loginByPhone("17600697395", "Haoliang0423")
                .async()
                .subscribe(object : BaseObserverWithoutBaseResponse<ResponseBody>() {
                    override fun next(data: ResponseBody) {
                        data.string().log()
                    }
                })

    }
}
