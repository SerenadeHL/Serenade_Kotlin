package me.serenadehl.serenade_kotlin.extensions

import java.security.MessageDigest

fun String.md5(): String {
    try {
        // Create MD5 Hash
        val digest = MessageDigest.getInstance("MD5")
        digest.update(this.toByteArray())
        val messageDigest = digest.digest()

        // Create Hex String
        val hexString = StringBuffer()
        for (i in messageDigest.indices)
            hexString.append(Integer.toHexString(0xFF and messageDigest[i].toInt()))
        return hexString.toString()

    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
    return ""
}