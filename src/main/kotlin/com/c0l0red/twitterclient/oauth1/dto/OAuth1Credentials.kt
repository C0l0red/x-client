package com.c0l0red.twitterclient.oauth1.dto

import com.c0l0red.twitterclient.oauth1.algorithms.HashingAlgorithm
import com.c0l0red.twitterclient.oauth1.algorithms.HmacSha1HashingAlgorithm
import com.c0l0red.twitterclient.utils.Randoms
import java.time.Instant

data class OAuth1Credentials(
    private val signatureMethod: HashingAlgorithm.Method,
    private val consumerKey: String,
    private val consumerSecret: String,
    private val accessToken: String,
    private val tokenSecret: String,
    private val callbackUrl: String,
    private val verifier: String,
    private val timestamp: String = Instant.now().epochSecond.toString(),
    private var nonce: String? = null,
    private var signature: String? = null,
) {
    private val hashingAlgorithm: HashingAlgorithm
        get(): HashingAlgorithm = when(signatureMethod) {
            HashingAlgorithm.Method.HmacSHA1 -> HmacSha1HashingAlgorithm
            else -> throw Exception("Invalid Hashing Algorithm")
        }
    init {
        nonce = generateNonce()
        signature = generateSignature()
    }

    private fun generateNonce(): String = Randoms.generateAlphanumericString(15) + timestamp

    private fun generateSignature(): String {
        return hashingAlgorithm.hash("")
    }
}
