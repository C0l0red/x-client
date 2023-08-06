package com.c0l0red.twitterclient.oauth1.algorithms

sealed interface HashingAlgorithm {
    val method: Method

    fun hash(value: String): String

    enum class Method {
        HmacSHA1,
        PLAINTEXT
    }
}