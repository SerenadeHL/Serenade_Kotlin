package me.serenadehl.serenade_kotlin.retrofit

import android.util.Base64
import io.reactivex.Observable
import me.serenadehl.serenade_kotlin.constant.SPConst
import me.serenadehl.serenade_kotlin.extensions.log
import me.serenadehl.serenade_kotlin.extensions.md5
import me.serenadehl.serenade_kotlin.utils.sharedpre.SPUtil
import okhttp3.ResponseBody
import java.math.BigInteger
import java.nio.charset.Charset
import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.experimental.xor

object Requests {
    val modulus = "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7"
    val nonce = "0CoJUm6Qyw8W8jud"
    val pubKey = "010001"
    /**
     * AES/CBC/NoPadding加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    fun encrypt(src: String, key: String): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/NoPadding")
        val blockSize = cipher.blockSize
        val dataBytes = src.toByteArray()
        var plaintextLength = dataBytes.size
        if (plaintextLength % blockSize != 0) {
            plaintextLength += (blockSize - plaintextLength % blockSize)
        }
        val plaintext = ByteArray(plaintextLength)
        System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.size)
        val keyspec = SecretKeySpec(key.toByteArray(), "AES")
        val ivspec = IvParameterSpec("0102030405060708".toByteArray())
        cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec)
        val encrypted = cipher.doFinal(plaintext)
        return encrypted
    }

    /**
     * 把字符串的每个字符转换成16进制ASCII码
     */
    fun hexlify(src: String): String {
        val sb = StringBuilder()
        for (a in src)
            sb.append(Integer.toHexString(a.toInt()))
        return sb.toString()
    }

    fun aesEncrypt(text: String, secKey: String): String {
        val pad = 16 - text.length % 16
        val chr = pad.toChar()//余数作为ASCII码转换成对应字符
        val sb = StringBuilder(text)
        for (i in 0 until pad)
            sb.append(chr)//拼接余数个字符
        return Base64.encodeToString(encrypt(sb.toString(), secKey), Base64.NO_WRAP)
    }

    /**
     * 随机生成16位16进制字符串
     */
    fun createSecretKey(size: Int): String {
        val result = StringBuilder()
        val radom = Random()
        for (i in 0 until size) {
            result.append(Integer.toHexString(radom.nextInt(16)))
        }
        return result.toString()
    }

    fun rsaEncrypt(text: String, pubKey: String, modulus: String): String {
        val reverse = text.reversed()
        val a = BigInteger(hexlify(reverse), 16)
        val b = BigInteger(pubKey, 16)
        val c = BigInteger(modulus, 16)
        val rs = a.modPow(b, c)//a的b次方%c
        return rs.toString(16).format("%0256d")
    }

    /**
     * 加密歌曲id
     */
    fun encrypted_id(id: String): String {
        val magic = "3go8&$8*3*3h0k(2)2".toByteArray(Charset.forName("utf-8"))
        val song_id = id.toByteArray(Charset.forName("utf-8"))
        val magic_len = magic.size
        for ((i, sid) in song_id.withIndex()) {
            song_id[i] = sid xor magic[i % magic_len]
        }
        var result = Base64.encodeToString(song_id.md5(), Base64.NO_WRAP)
        return result.replace("/", "_").replace("+", "-")
    }

    //手机号登录
    fun loginByPhone(phone: String, password: String): Observable<ResponseBody> {
        val json = """{phone:"$phone",password:"${password.md5()}",rememberLogin:"true"}"""
        val secKey = createSecretKey(16)
        val encText = aesEncrypt(aesEncrypt(json, nonce), secKey)
        val encSecKey = rsaEncrypt(secKey, pubKey, modulus)
        return RetrofitHelper.api.loginByPhone(encText, encSecKey)
    }

    //私信
    fun sendText(msg: String, userIds: String): Observable<ResponseBody> {
        val json = """{type:"text",msg:"$msg",userIds:"[$userIds]",csrf_token:"${SPUtil.getString(SPConst.CSRF_TOKEN)}"}"""
        val secKey = createSecretKey(16)
        val encText = aesEncrypt(aesEncrypt(json, nonce), secKey)
        val encSecKey = rsaEncrypt(secKey, pubKey, modulus)
        return RetrofitHelper.api.sendText(encText, encSecKey)
    }

    //带歌单的私信(不能发送重复的歌单)
    fun sendPlayList(playListId: String, msg: String, userIds: String): Observable<ResponseBody> {
        val json = """{id:"$playListId",type:"playlist",msg:"$msg",userIds:"[$userIds]",csrf_token:"${SPUtil.getString(SPConst.CSRF_TOKEN)}"}"""
        val secKey = createSecretKey(16)
        val encText = aesEncrypt(aesEncrypt(json, nonce), secKey)
        val encSecKey = rsaEncrypt(secKey, pubKey, modulus)
        return RetrofitHelper.api.sendText(encText, encSecKey)
    }

}