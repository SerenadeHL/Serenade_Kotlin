package me.serenadehl.serenade_kotlin.base.mvpbase

interface IBaseView

interface IBasePresenter {
    fun attach(view: IBaseView)
    fun detach()
}

interface IBaseModel
