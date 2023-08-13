package com.c0l0red.twitterclient.oauth1.algorithms

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object HmacSha1HashingAlgorithm: HashingAlgorithm {
    override val method: HashingAlgorithm.Method = HashingAlgorithm.Method.HmacSHA1

    override fun hash(value: String, key: String): ByteArray {
        val secret = SecretKeySpec(key.toByteArray(), method.pascalCase)
        val mac = Mac.getInstance(method.pascalCase)
        mac.init(secret)
        return mac.doFinal(value.toByteArray())
    }
}