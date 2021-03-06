package me.serenadehl.serenade_kotlin.retrofit

import me.serenadehl.serenade_kotlin.BuildConfig
import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-21 15:36:27
 */

object RetrofitHelper {
    private const val DEFAULT_TIMEOUT = 5L
    private const val BASE_URL = "http://music.163.com/"
    private const val BASE_URL_DEBUG = "http://music.163.com/"

    private val mRetrofit: Retrofit by lazy {
        var okhttpClient = OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(InterceptorUtil.getHeaderInterceptor())
                .addInterceptor(InterceptorUtil.getLogInterceptor())//添加日志拦截器
                .build()

        Retrofit.Builder()
                .baseUrl(if (BuildConfig.DEBUG) BASE_URL_DEBUG else BASE_URL)//设置项目总地址
                .addConverterFactory(GsonConverterFactory.create())//添加Gson解析工厂
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加RxJava2转换器
                .client(okhttpClient)
                .build()
    }
    private val mRetrofitAPI: RetrofitApi by lazy { mRetrofit.create(RetrofitApi::class.java) }

    val api get() = mRetrofitAPI
}
