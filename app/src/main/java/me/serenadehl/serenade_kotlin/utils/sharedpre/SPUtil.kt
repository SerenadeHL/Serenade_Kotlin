package me.serenadehl.shellaccount.utils.sharedpre

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import me.serenadehl.shellaccount.utils.app.AppManager

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-15 00:50:35
 */
object SPUtil {
    private val SP_NAME = "Serenade"
    private val mSharedPreferences: SharedPreferences by lazy {
        AppManager.instance
                .currentActivity
                .applicationContext
                .getSharedPreferences(SP_NAME, MODE_PRIVATE)
    }

    fun putBoolean(key: String, value: Boolean) {
        mSharedPreferences
                .edit()
                .putBoolean(key, value)
                .apply()
    }

    fun getBoolean(key: String, defValue: Boolean = false) = mSharedPreferences.getBoolean(key, defValue)

    fun putString(key: String, value: String) {
        mSharedPreferences
                .edit()
                .putString(key, value)
                .apply()
    }

    fun getString(key: String, defValue: String = "") = mSharedPreferences.getString(key, defValue)

    fun putInt(key: String, value: Int) {
        mSharedPreferences
                .edit()
                .putInt(key, value)
                .apply()
    }

    fun getInt(key: String, defValue: Int = -1) = mSharedPreferences.getInt(key, defValue)

    fun putFloat(key: String, value: Float) {
        mSharedPreferences
                .edit()
                .putFloat(key, value)
                .apply()
    }

    fun getFloat(key: String, defValue: Float = -1F) = mSharedPreferences.getFloat(key, defValue)

    fun putLong(key: String, value: Long) {
        mSharedPreferences
                .edit()
                .putLong(key, value)
                .apply()
    }

    fun getLong(key: String, defValue: Long = -1L) = mSharedPreferences.getLong(key, defValue)

    fun putArrayList(key: String, value: ArrayList<out Any>) = putString(key, Gson().toJson(value))

    fun <T> getArrayList(key: String) = Gson().fromJson(mSharedPreferences.getString(key, "[]"), ArrayList::class.java) as ArrayList<*>
}