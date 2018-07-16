package me.serenadehl.serenade_kotlin.extensions

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import me.serenadehl.serenade_kotlin.BuildConfig
import me.serenadehl.serenade_kotlin.utils.app.AppManager
import me.serenadehl.serenade_kotlin.utils.sharedpre.SPUtil
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
    (applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * 显示输入法
 *
 * @param view
 */
inline fun Context.showKeyboard(view: View) {
    (applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(view, 0)
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

/**
 * 获取屏幕宽度,包含NavigationBar
 */
inline fun Activity.getScreenWidth(): Int {
    var dpi = 0
    val display = windowManager.defaultDisplay
    val dm = DisplayMetrics()
    val c: Class<*>
    try {
        c = Class.forName("android.view.Display")
        val method = c.getMethod("getRealMetrics", DisplayMetrics::class.java)
        method.invoke(display, dm)
        dpi = dm.widthPixels
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return dpi
}

/**
 * 获取屏幕高度,包含NavigationBar
 */
inline fun Activity.getScreenHeight(): Int {
    var dpi = 0
    val display = windowManager.defaultDisplay
    val dm = DisplayMetrics()
    val c: Class<*>
    try {
        c = Class.forName("android.view.Display")
        val method = c.getMethod("getRealMetrics", DisplayMetrics::class.java)
        method.invoke(display, dm)
        dpi = dm.heightPixels
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return dpi
}

/**
 * 判断APP是否在前台
 */
inline fun Context.isRunningForeground(packageName: String): Boolean {
    val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val cn = am.getRunningTasks(1)[0].topActivity
    val currentPackageName = cn.packageName
    return currentPackageName != null && currentPackageName == packageName
}

/**
 * 获取版本号
 */
inline fun Context.getVersionName(): String {
    // getPackageName()是你当前类的包名，0代表是获取版本信息
    val packInfo = packageManager.getPackageInfo(packageName, 0)
    return packInfo.versionName
}

/**
 * 上一次点击退出按钮的时间
 */
private var mLastBackPressedTime: Long = 0

/**
 * 双击返回退出App
 */
fun Context.exitApp() {
    if (System.currentTimeMillis() - mLastBackPressedTime > 2000) {
        toast("再按一次退出程序")
        mLastBackPressedTime = System.currentTimeMillis()
    } else {
        AppManager.instance.exitApp()
    }
}

/**
 * 拨打电话
 */
inline fun Context.callPhone(phone: String) = startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")))

/**
 * 发送短信
 */
inline fun Context.sendMessage(phone: String) = startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("smsto:$phone")))
