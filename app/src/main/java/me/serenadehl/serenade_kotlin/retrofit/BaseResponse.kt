package me.serenadehl.serenade_kotlin.retrofit

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-05-05 20:27:47
 */
data class BaseResponse<out T>(val result: T, val code: Int)