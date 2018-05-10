package me.serenadehl.serenade_kotlin.retrofit

import android.util.Log

import java.io.IOException

import me.serenadehl.serenade_kotlin.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import javax.crypto.Cipher

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-21 16:22:08
 */

object InterceptorUtil {
    private val TAG = "=========="

    //日志拦截器
    fun LogInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.i(TAG, "" + message) })
                .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)//设置打印数据的级别
    }

    fun HeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.addHeader("Cookie", "appver=1.5.2;")
                    .addHeader("Referer", "http://music.163.com/")
            chain.proceed(builder.build())
        }
    }
}
