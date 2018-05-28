package me.serenadehl.serenade_kotlin.ui

import me.serenadehl.serenade_kotlin.base.mvpbase.MVPBasePresenter

class MainPresenter : MVPBasePresenter<MainActivity, MainModel>(),IMainPresenter {
    override fun createModel() = MainModel()

    override fun start() {

    }
}