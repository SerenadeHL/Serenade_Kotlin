package me.serenadehl.serenade_kotlin

import android.app.Application
import me.serenadehl.serenade_kotlin.utils.app.ActivityLifecycleCallback

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-21 16:55:25
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(ActivityLifecycleCallback())
    }
}