package me.serenadehl.serenade_kotlin.retrofit

import android.util.ArrayMap

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * 网络请求管理类
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-21 16:25:10
 */


class NetworkManager private constructor() {
    private val mDisposables by lazy { ArrayMap<String, CompositeDisposable>() }//按类名以及该类中的网络请求集合存储

    private object NetworkManagerHolder {
        val INSTANCE = NetworkManager()
    }

    companion object {
        val instance: NetworkManager get() = NetworkManagerHolder.INSTANCE
    }


    fun cancelRequests(className: String) {
        mDisposables[className]?.dispose()
    }

    fun recordRequest(className: String, d: Disposable) {
        (mDisposables[className] ?: CompositeDisposable().apply { mDisposables[className] = this }).add(d)
    }

    fun removeRequest(className: String, d: Disposable) {
        mDisposables[className]?.remove(d)
    }
}
