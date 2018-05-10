package me.serenadehl.serenade_kotlin.utils.log

import android.util.Log
import me.serenadehl.serenade_kotlin.BuildConfig

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-21 15:20:43
 */

object L {
    private const val TAG = "========="

    fun e(tag: String, content: String) {
        if (BuildConfig.DEBUG) Log.e(tag, content)
    }

    fun d(tag: String, content: String) {
        if (BuildConfig.DEBUG) Log.d(tag, content)
    }

    fun i(tag: String, content: String) {
        if (BuildConfig.DEBUG) Log.i(tag, content)
    }

    fun w(tag: String, content: String) {
        if (BuildConfig.DEBUG) Log.w(tag, content)
    }

    fun v(tag: String, content: String) {
        if (BuildConfig.DEBUG) Log.v(tag, content)
    }

    fun e(content: String) {
        if (BuildConfig.DEBUG) Log.e(TAG, content)
    }

    fun d(content: String) {
        if (BuildConfig.DEBUG) Log.d(TAG, content)
    }

    fun i(content: String) {
        if (BuildConfig.DEBUG) Log.i(TAG, content)
    }

    fun w(content: String) {
        if (BuildConfig.DEBUG) Log.w(TAG, content)
    }

    fun v(content: String) {
        if (BuildConfig.DEBUG) Log.v(TAG, content)
    }
}
