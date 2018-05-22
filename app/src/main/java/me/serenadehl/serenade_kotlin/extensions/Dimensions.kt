package me.serenadehl.shellaccount.extensions

import android.app.Fragment
import android.content.Context
import android.support.annotation.DimenRes
import android.view.View

//returns dip(dp) dimension value in pixels
inline fun Context.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()

inline fun Context.dip(value: Float): Int = (value * resources.displayMetrics.density).toInt()

//return sp dimension value in pixels
inline fun Context.sp(value: Int): Int = (value * resources.displayMetrics.scaledDensity).toInt()

inline fun Context.sp(value: Float): Int = (value * resources.displayMetrics.scaledDensity).toInt()

//converts px value into dip or sp
inline fun Context.px2dip(px: Int): Float = px.toFloat() / resources.displayMetrics.density

inline fun Context.px2sp(px: Int): Float = px.toFloat() / resources.displayMetrics.scaledDensity

inline fun Context.dimen(@DimenRes resource: Int): Int = resources.getDimensionPixelSize(resource)


//the same for the views
inline fun View.dip(value: Int): Int = context.dip(value)

inline fun View.dip(value: Float): Int = context.dip(value)
inline fun View.sp(value: Int): Int = context.sp(value)
inline fun View.sp(value: Float): Int = context.sp(value)
inline fun View.px2dip(px: Int): Float = context.px2dip(px)
inline fun View.px2sp(px: Int): Float = context.px2sp(px)
inline fun View.dimen(@DimenRes resource: Int): Int = context.dimen(resource)

//the same for Fragments
inline fun Fragment.dip(value: Int): Int = activity.dip(value)

inline fun Fragment.dip(value: Float): Int = activity.dip(value)
inline fun Fragment.sp(value: Int): Int = activity.sp(value)
inline fun Fragment.sp(value: Float): Int = activity.sp(value)
inline fun Fragment.px2dip(px: Int): Float = activity.px2dip(px)
inline fun Fragment.px2sp(px: Int): Float = activity.px2sp(px)
inline fun Fragment.dimen(@DimenRes resource: Int): Int = activity.dimen(resource)