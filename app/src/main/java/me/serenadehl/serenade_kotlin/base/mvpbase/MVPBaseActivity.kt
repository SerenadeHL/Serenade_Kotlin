package me.serenadehl.serenade_kotlin.base.mvpbase

import android.os.Bundle
import me.serenadehl.serenade_kotlin.base.BaseActivity

abstract class MVPBaseActivity<P : MVPBasePresenter<IBaseView,IBaseModel>> : BaseActivity(), IBaseView {
    val mPresenter: P by lazy {
        createPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mPresenter.attach(this)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        mPresenter.detach()
        super.onDestroy()
    }

    abstract fun createPresenter(): P
}