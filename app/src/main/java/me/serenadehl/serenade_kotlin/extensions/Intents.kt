package me.serenadehl.shellaccount.extensions

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable
import android.app.Fragment as AppFragment
import android.support.v4.app.Fragment as SupportFragment

fun fillIntentArguments(intent: Intent, params: Array<out Pair<String, Any?>>) {
    params.forEach {
        val value = it.second
        when (value) {
            null -> intent.putExtra(it.first, null as Serializable?)
            is Int -> intent.putExtra(it.first, value)
            is Long -> intent.putExtra(it.first, value)
            is CharSequence -> intent.putExtra(it.first, value)
            is String -> intent.putExtra(it.first, value)
            is Float -> intent.putExtra(it.first, value)
            is Double -> intent.putExtra(it.first, value)
            is Char -> intent.putExtra(it.first, value)
            is Short -> intent.putExtra(it.first, value)
            is Boolean -> intent.putExtra(it.first, value)
            is Serializable -> intent.putExtra(it.first, value)
            is Bundle -> intent.putExtra(it.first, value)
            is Parcelable -> intent.putExtra(it.first, value)
            is Array<*> -> when {
                value.isArrayOf<CharSequence>() -> intent.putExtra(it.first, value)
                value.isArrayOf<String>() -> intent.putExtra(it.first, value)
                value.isArrayOf<Parcelable>() -> intent.putExtra(it.first, value)
                else -> throw Exception("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
            }
            is IntArray -> intent.putExtra(it.first, value)
            is LongArray -> intent.putExtra(it.first, value)
            is FloatArray -> intent.putExtra(it.first, value)
            is DoubleArray -> intent.putExtra(it.first, value)
            is CharArray -> intent.putExtra(it.first, value)
            is ShortArray -> intent.putExtra(it.first, value)
            is BooleanArray -> intent.putExtra(it.first, value)
            else -> throw Exception("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
        }
        return@forEach
    }
}

inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any?>) {
    startActivity(Intent(this, T::class.java).apply {
        if (params.isNotEmpty()) fillIntentArguments(this, params)
    })
}

inline fun <reified T : Activity> SupportFragment.startActivity(vararg params: Pair<String, Any?>) {
    startActivity(Intent(activity, T::class.java).apply {
        if (params.isNotEmpty()) fillIntentArguments(this, params)
    })
}

inline fun <reified T : Activity> AppFragment.startActivity(vararg params: Pair<String, Any?>) {
    startActivity(Intent(activity, T::class.java).apply {
        if (params.isNotEmpty()) fillIntentArguments(this, params)
    })
}

inline fun <reified T : Service> Context.startService(vararg params: Pair<String, Any?>) {
    startService(Intent(this, T::class.java).apply {
        if (params.isNotEmpty()) fillIntentArguments(this, params)
    })
}

inline fun <reified T : Service> SupportFragment.startService(vararg params: Pair<String, Any?>) {
    activity?.startService<T>(*params)
}

inline fun <reified T : Service> AppFragment.startService(vararg params: Pair<String, Any?>) {
    activity?.startService<T>(*params)
}

inline fun <reified T : Service> Context.stopService(vararg params: Pair<String, Any?>) {
    stopService(Intent(this, T::class.java).apply {
        if (params.isNotEmpty()) fillIntentArguments(this, params)
    })
}
