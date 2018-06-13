package me.serenadehl.serenade_kotlin.ui

import me.serenadehl.serenade_kotlin.R
import me.serenadehl.serenade_kotlin.base.mvpbase.*

class MainActivity : MVPBaseActivity<MainPresenter>(), IMainView {
    override fun createPresenter() = MainPresenter()

    override fun layout() = R.layout.activity_main

    override fun onActivityCreated() {
        mPresenter.loginByPhone("17600697395", "Haoliang0423")
    }
}
