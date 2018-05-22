package me.serenadehl.shellaccount.extensions

import android.view.View

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-05-05 19:49:23
 */

inline fun View.gone() = apply { visibility = View.GONE }

inline fun View.visible() = apply { visibility = View.VISIBLE }

inline fun View.invisible() = apply { visibility = View.INVISIBLE }

inline fun View.setSize(width: Int = layoutParams.width, height: Int = layoutParams.height) {
    layoutParams.apply {
        this.width = width
        this.height = height
    }
}