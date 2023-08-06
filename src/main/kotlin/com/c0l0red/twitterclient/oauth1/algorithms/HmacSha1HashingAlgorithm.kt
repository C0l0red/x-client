package com.c0l0red.twitterclient.oauth1.algorithms

import javax.crypto.Mac

import javax.crypto.spec.SecretKeySpec

object HmacSha1HashingAlgorithm: HashingAlgorithm {
    override val method: HashingAlgorithm.Method = HashingAlgorithm.Method.HmacSHA1
    private const val key = "secretKey"
    private val hexArray = "0123456789abcdef".toCharArray()

    override fun hash(value: String): String {
        val secret = SecretKeySpec(key.toByteArray(), method.name)
        val mac = Mac.getInstance(method.name)
        mac.init(secret)
        val bytes = mac.doFinal(value.toByteArray())
        return bytesToHex(bytes)
    }

    private fun bytesToHex(bytes: ByteArray): String {
        val hexChars = CharArray(bytes.size * 2)
        var v: Int
        for (j in bytes.indices) {
            v = bytes[j].toInt() and 0xFF
            hexChars[j * 2] = hexArray[v ushr 4]
            hexChars[j * 2 + 1] = hexArray[v and 0x0F]
        }
        return String(hexChars)
    }
}