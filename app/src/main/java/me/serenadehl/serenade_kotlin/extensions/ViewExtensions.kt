package me.serenadehl.serenade_kotlin.extensions

import android.view.View

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-05-05 19:49:23
 */

fun View.gone() = apply { visibility = View.GONE }

fun View.visible() = apply { visibility = View.VISIBLE }

fun View.invisible() = apply { visibility = View.INVISIBLE }

fun View.setSize(width: Int = layoutParams.width, height: Int = layoutParams.height) {
    layoutParams.apply {
        this.width = width
        this.height = height
    }
}