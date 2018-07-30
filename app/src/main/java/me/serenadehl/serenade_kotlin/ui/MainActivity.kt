package me.serenadehl.serenade_kotlin.ui

import android.os.Bundle
import me.serenadehl.serenade_kotlin.R
import me.serenadehl.serenade_kotlin.base.mvpbase.*
import me.serenadehl.serenade_kotlin.extensions.callPhone
import me.serenadehl.serenade_kotlin.extensions.sendMessage

class MainActivity : MVPBaseActivity<MainPresenter>(), IMainView {
    override fun createPresenter() = MainPresenter()

    override fun layout() = R.layout.activity_main

    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        mPresenter.loginByPhone("17600697395", "Haoliang0423")
    }
}
