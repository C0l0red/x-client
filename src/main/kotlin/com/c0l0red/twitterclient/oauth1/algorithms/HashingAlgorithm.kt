package com.c0l0red.twitterclient.oauth1.algorithms

sealed interface HashingAlgorithm {
    val method: Method

    fun hash(value: String, key: String): ByteArray

    enum class Method(val pascalCase: String, val upperCase: String) {
        HmacSHA1("HmacSHA1", "HMAC-SHA1")
    }
}