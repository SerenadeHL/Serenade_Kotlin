package me.serenadehl.shellaccount.extensions

import java.security.MessageDigest

inline fun String.md5(): String {
    try {
        // Create MD5 Hash
        val digest = MessageDigest.getInstance("MD5")
        val messageDigest = digest.digest(this.toByteArray())
        // Create Hex String
        val hexString = StringBuffer()
        for (i in messageDigest.indices)
            hexString.append(Integer.toHexString(0xFF and messageDigest[i].toInt()))
        return hexString.toString()
    } catch (e: Exception) {
        throw e
    }
}

inline fun ByteArray.md5(): ByteArray {
    try {
        val md = MessageDigest.getInstance("MD5")
        return md.digest(this)
    } catch (e: Exception) {
        throw e
    }
}

