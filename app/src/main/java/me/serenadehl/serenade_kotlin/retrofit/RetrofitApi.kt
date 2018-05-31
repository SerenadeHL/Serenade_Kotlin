package me.serenadehl.serenade_kotlin.retrofit

import io.reactivex.Observable
import okhttp3.ResponseBody
import okhttp3.RequestBody
import retrofit2.http.*


/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-21 15:35:33
 */

interface RetrofitApi {
    //手机号登录
    @FormUrlEncoded
    @POST("weapi/login/cellphone")
    fun loginByPhone(@Field("params") params: String, @Field("encSecKey") encSecKey: String): Observable<ResponseBody>

    //私信
    @FormUrlEncoded
    @POST("weapi/msg/private/send")
    fun sendText(@Field("params") params: String, @Field("encSecKey") encSecKey: String): Observable<ResponseBody>
}
