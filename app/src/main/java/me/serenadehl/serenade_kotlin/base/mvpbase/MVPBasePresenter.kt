package me.serenadehl.serenade_kotlin.base.mvpbase

import java.lang.ref.WeakReference


abstract class MVPBasePresenter<V : IBaseView, M : IBaseModel> : IBasePresenter {
    lateinit var mView: WeakReference<V>
    val mModel: M by lazy { createModel() }

    fun attach(view: V) {
        this.mView = WeakReference(view)
    }

    fun detach() {
        mView.clear()
    }

    fun getView() = mView.get()

    fun getModel() = mModel

    abstract fun createModel(): M
}