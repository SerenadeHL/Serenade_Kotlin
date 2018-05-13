package me.serenadehl.serenade_kotlin.retrofit

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

import io.reactivex.Observer
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import me.serenadehl.serenade_kotlin.extensions.toast
import me.serenadehl.serenade_kotlin.utils.app.AppManager
import me.serenadehl.serenade_kotlin.utils.log.L

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-21 16:24:40
 */

abstract class BaseObserver<T> : Observer<BaseResponse<T>> {
    private lateinit var disposable: Disposable

    override fun onError(e: Throwable) {
        e.printStackTrace()
        when (e) {
            is SocketTimeoutException -> AppManager.instance.currentActivity.toast("请求超时！")
            is ConnectException -> AppManager.instance.currentActivity.toast("网络中断，请检查您的网络状态！")
            is UnknownHostException -> AppManager.instance.currentActivity.toast("网络错误，请检查您的网络状态！")
            else -> {
                L.e("-----RxJava-----", "error----------->" + e.toString())
            }
        }
        error(e)
    }

    open fun error(error: Throwable) {

    }

    override fun onSubscribe(@NonNull d: Disposable) {
        disposable = d
    }

    override fun onNext(t: BaseResponse<T>) {
        next(t.result)
    }

    abstract fun next(@NonNull data: T)

    override fun onComplete() {
        complete()
    }

    open fun complete() {

    }
}
