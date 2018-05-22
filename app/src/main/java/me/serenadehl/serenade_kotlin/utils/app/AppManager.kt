package me.serenadehl.shellaccount.utils.app

import android.app.Activity
import android.content.Intent
import java.util.*

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-15 00:55:56
 */

class AppManager private constructor() {

    private object AppManagerHolder {
        val INSTANCE = AppManager()
    }

    companion object {
        private val activities: Stack<Activity> by lazy { Stack<Activity>() }

        val instance: AppManager get() = AppManagerHolder.INSTANCE
    }

    /**
     * 获取当前Activity
     */
    val currentActivity: Activity get() = activities.lastElement()

    /**
     * 获取Activity栈
     */
    val activityStack: Stack<Activity> get() = activities

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    /**
     * 从堆栈中移除Activity
     */
    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(cls: Class<out Activity>) {
        activities
                .filter { cls.name == it.javaClass.name }
                .forEach { it.finish() }
    }

    /**
     * 结束当前Activity
     */
    fun finishCurrentActivity() {
        activities.pop().finish()
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        activities.forEach { it.finish() }
        activities.clear()
    }

    /**
     * 退出App
     */
    fun exitApp() {
        finishAllActivity()
        System.exit(0)
    }

    /**
     * 重启App
     */
    fun rebootApp() {
        val activity = currentActivity
        val intent = activity.baseContext.packageManager.getLaunchIntentForPackage(activity.baseContext.packageName)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        activity.startActivity(intent)
        System.exit(0)
    }
}
