package me.serenadehl.serenade_kotlin.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment as SupportFragment
import android.app.Fragment as AppFragment
import android.widget.Toast

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-14 22:47:26
 */

/**
 * Toast
 */
fun Context.toast(msg: String) = Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()

fun SupportFragment.toast(msg: String) = Toast.makeText(activity?.applicationContext, msg, Toast.LENGTH_SHORT).show()

fun AppFragment.toast(msg: String) = Toast.makeText(activity?.applicationContext, msg, Toast.LENGTH_SHORT).show()

/**
 * 跳转
 */
fun Context.startActivity(activity: Class<out Activity>, bundle: Bundle? = null) = startActivity(Intent(this, activity).apply { putExtras(bundle) })

fun SupportFragment.startActivity(activity: Class<out Activity>, bundle: Bundle? = null) = startActivity(Intent(getActivity(), activity).apply { putExtras(bundle) })

fun AppFragment.startActivity(activity: Class<out Activity>, bundle: Bundle? = null) = startActivity(Intent(getActivity(), activity).apply { putExtras(bundle) })

/**
 * 设置虚拟按键颜色
 */
fun Activity.setNavigationBarColor(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.navigationBarColor = color
    }
}