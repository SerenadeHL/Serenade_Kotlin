package me.serenadehl.shellaccount.extensions

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import me.serenadehl.shellaccount.BuildConfig
import me.serenadehl.shellaccount.utils.sharedpre.SPUtil
import android.app.Fragment as AppFragment
import android.support.v4.app.Fragment as SupportFragment

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-14 22:47:26
 */

/**
 * Toast
 */
inline fun Context.toast(msg: String) = Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()

inline fun SupportFragment.toast(msg: String) = Toast.makeText(activity?.applicationContext, msg, Toast.LENGTH_SHORT).show()

inline fun AppFragment.toast(msg: String) = Toast.makeText(activity?.applicationContext, msg, Toast.LENGTH_SHORT).show()

/**
 * 隐藏输入法
 *
 * @param view
 */
inline fun Context.hideKeyboard(view: View) {
    val imm = applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * 显示输入法
 *
 * @param view
 */
inline fun Context.showKeyboard(view: View) {
    val imm = applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(view, 0)
}

inline fun SupportFragment.hideKeyboard(view: View) = activity?.hideKeyboard(view)

inline fun SupportFragment.showKeyboard(view: View) = activity?.showKeyboard(view)

inline fun AppFragment.hideKeyboard(view: View) = activity?.hideKeyboard(view)

inline fun AppFragment.showKeyboard(view: View) = activity?.showKeyboard(view)


/**
 * 设置虚拟按键颜色
 */
inline fun Activity.setNavigationBarColor(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.navigationBarColor = color
    }
}

/**
 * log
 */
inline fun Any.log() {
    if (BuildConfig.DEBUG) Log.e("=========", toString())
}

/**
 * 保存到SP
 */
inline fun <T> T.saveToSP(key: String) = apply { SPUtil.putString(key, toString()) }