package me.serenadehl.shellaccount.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.*

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


    /**
     * 获得StatusBar的高度
     *
     * @return StatusBar的高度
     */
    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    /**
     * 设置StatusBar透明
     *
     * @param darkFont 是否使用深色状态栏字体
     */
    fun setTranslucentStatus(darkFont: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0 全透明实现
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            if (darkFont)
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            else
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4 全透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }


    /**
     * 设置状态栏颜色
     *
     * @param color 颜色
     * @param darkFont 是否使用深色状态栏字体
     */
    fun setStatusBarColor(color: Int, darkFont: Boolean = false) {
        setTranslucentStatus(darkFont)
        val contentLayout = findViewById<View>(android.R.id.content) as ViewGroup
        setupStatusBarView(contentLayout, color)
        var contentView = contentLayout.getChildAt(1)
        //如果是DrawerLayout,让内部第一个布局设置padding
        if (contentView is DrawerLayout)
            contentView = contentView.getChildAt(0)
        val params = contentView.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(0, getStatusBarHeight(), 0, 0)
    }

    /**
     * 创建view替换StatusBar
     *
     * @param contentLayout
     * @param color
     */
    private fun setupStatusBarView(contentLayout: ViewGroup, color: Int) {
        val statusBarView = View(this)
        val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight())
        contentLayout.addView(statusBarView, 0, lp)
        statusBarView.setBackgroundColor(color)
    }
}
