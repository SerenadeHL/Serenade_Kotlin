package me.serenadehl.serenade_kotlin.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.IntegerRes
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.Window

abstract class BaseActivity : AppCompatActivity() {
    lateinit var mRootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //取消ActionBar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        mRootView = LayoutInflater.from(this).inflate(layout(), null)
        setContentView(mRootView)
        onActivityCreated()
    }

    /**
     * 设置布局
     * @return 布局id
     */
    abstract fun layout(): Int

    abstract fun onActivityCreated()
}
