package me.serenadehl.serenade_kotlin.base.mvpbase

import java.lang.ref.WeakReference

abstract class MVPBasePresenter<V : IBaseView, M : IBaseModel> : IBasePresenter {
    lateinit var mView: WeakReference<V>
    var mModel: M

    init {
        mModel = this.createModel()
    }

    @Suppress("UNCHECKED_CAST")
    override fun attach(view: IBaseView) {
        this.mView = WeakReference(view as V)
    }

    override fun detach() {
        mView.clear()
    }

    fun getView() = mView.get()

    fun getModel() = mModel

    abstract fun createModel(): M
}