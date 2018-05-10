package me.serenadehl.serenade_kotlin

import android.support.annotation.IntegerRes
import android.util.Base64
import me.serenadehl.serenade_kotlin.base.BaseActivity
import me.serenadehl.serenade_kotlin.extensions.async
import me.serenadehl.serenade_kotlin.retrofit.BaseObserverWithoutBaseResponse
import me.serenadehl.serenade_kotlin.retrofit.RetrofitHelper
import me.serenadehl.serenade_kotlin.utils.log.L
import okhttp3.MediaType
import okhttp3.ResponseBody
import okhttp3.RequestBody
import java.math.BigInteger
import java.nio.charset.Charset
import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec


class MainActivity : BaseActivity() {
    override fun layout() = R.layout.activity_main

    override fun onActivityCreated() {
        var json = "{'username':'17600697395','password':'Haoliang0423','rememberLogin':'true'}"
        json = encrypted_request(json)
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        RetrofitHelper.api
                .loginByPhone(body)
                .async()
                .subscribe(object : BaseObserverWithoutBaseResponse<ResponseBody>() {
                    override fun next(data: ResponseBody) {
                        L.e(data.string())
                    }
                })
    }


    val modulus = "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7"
    val nonce = "0CoJUm6Qyw8W8jud"
    val pubKey = "010001"


    fun encrypted_request(text: String): String {
        val secKey = createSecretKey(16)
        val encText = aesEncrypt(aesEncrypt(text, nonce), secKey)
        val encSecKey = rsaEncrypt(secKey, pubKey, modulus)
        return "{'params':'$encText', 'encSecKey':'$encSecKey'}"
    }

    fun aesEncrypt(text: String, secKey: String): String {
        val pad = 16 - text.length % 16
        val tt = text + Integer.valueOf(pad) * pad
        return String(Base64.encode(encrypt(tt, secKey), Base64.NO_WRAP), Charset.forName("utf-8"))
    }

    fun rsaEncrypt(text: String, pubKey: String, modulus: String): String {
        val reverse = text.reversed()
        L.e("a==" + stringTo16(reverse))
//        val a = stringTo16(reverse).toLong(16).toDouble()
        val a = BigInteger(stringTo16(reverse), 16).toDouble()
        L.e("b==$pubKey")
//        val b = pubKey.toLong(16).toDouble()
        val b = BigInteger(pubKey, 16).toDouble()
//        val c = modulus.forEach { it.toLong(16) }
        val c = BigInteger(modulus, 16).toDouble()

        val rs = Math.pow(a, b) % c
        return Integer.toHexString(rs.toInt()).format("%0256d")
    }

    fun createSecretKey(size: Int): String {
        val str = StringBuilder()//定义变长字符串
        val random = Random()
        //随机生成数字，并添加到字符串
        for (i in 0..size) {
            str.append(random.nextInt(10))
        }
        return stringTo16(str.toString()).substring(0, 17)
    }

    fun stringTo16(s: String): String {
        var str = ""
        for (i in 0 until s.length) {
            val ch = Integer.parseInt(s[i].toString())
            val s4 = Integer.toHexString(ch)
            str += s4
        }
        return str
    }

    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    fun encrypt(content: String, password: String): ByteArray {
        var kgen: KeyGenerator? = null
        try {
            kgen = KeyGenerator.getInstance("AES")
            kgen!!.init(128, SecureRandom("0102030405060708".toByteArray()))
            val secretKey = kgen!!.generateKey()
            val enCodeFormat = secretKey.getEncoded()
            val key = SecretKeySpec(enCodeFormat, "AES")
            val cipher = Cipher.getInstance("AES")// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key)// 初始化
            val byteContent = content.toByteArray(charset("utf-8"))
            return cipher.doFinal(byteContent)//加密
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return byteArrayOf()
    }
}
