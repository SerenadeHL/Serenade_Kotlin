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

    @FormUrlEncoded
    @POST("weapi/login/cellphone")
    fun loginByPhone(@Field("phone") phone: String, @Field("password") password: String, @Field("rememberLogin") rememberLogin: Boolean): Observable<ResponseBody>


    @Headers("Content-Type: application/json", "Accept: application/json")//需要添加头
    @POST("weapi/login/cellphone")
    fun loginByPhone(@Body info: RequestBody): Observable<ResponseBody>    // 请求体味RequestBody 类型
}
