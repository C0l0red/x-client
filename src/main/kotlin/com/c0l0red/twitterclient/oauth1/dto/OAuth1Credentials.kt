package com.c0l0red.twitterclient.oauth1.dto

import com.c0l0red.twitterclient.exceptions.InternalServerError
import com.c0l0red.twitterclient.oauth1.algorithms.HashingAlgorithm
import com.c0l0red.twitterclient.oauth1.algorithms.HmacSha1HashingAlgorithm
import com.c0l0red.twitterclient.utils.generateAlphanumericString
import org.apache.catalina.util.URLEncoder
import org.springframework.http.HttpMethod
import java.net.URL
import java.nio.charset.Charset
import java.time.Instant
import java.util.*

data class OAuth1Credentials(
    private val oauthSignatureMethod: HashingAlgorithm.Method,
    private val oauthConsumerKey: String,
    private val oauthConsumerSecret: String,
    private val httpMethod: HttpMethod,
    private var url: String,
    private val parameters: Map<String, String>,
    private val oauthTimestamp: String = Instant.now().epochSecond.toString(),
    private val oauthVersion: String = "1.0",
    private val oauthNonce: String = generateAlphanumericString(15) + oauthTimestamp,
    private val oauthToken: String? = null,
    private val oauthTokenSecret: String? = null,
) {
    private val urlEncoder: URLEncoder = URLEncoder()

    private val oauthSignature: String
        get() = generateSignature()

    private val hashingAlgorithm: HashingAlgorithm
        get(): HashingAlgorithm = when (oauthSignatureMethod) {
            HashingAlgorithm.Method.HmacSHA1 -> HmacSha1HashingAlgorithm
        }

    init {
        normalizeUrl()
        urlEncoder.addSafeCharacter('-')
        urlEncoder.addSafeCharacter('_')
        urlEncoder.addSafeCharacter('.')
        urlEncoder.addSafeCharacter('~')
    }

    fun asForm(): Map<String, String> {
        return buildMap {
            put("oauth_consumer_key", oauthConsumerKey)
            put("oauth_signature_method", oauthSignatureMethod.upperCase)
            put("oauth_nonce", oauthNonce)
            put("oauth_version", oauthVersion)
            put("oauth_signature", oauthSignature)
             if (!oauthToken.isNullOrBlank()) put("oauth_token", oauthToken)
        }
    }

    fun asHeaderString(): String {
        return buildString {
            append("OAuth ")
            append("oauth_consumer_key=\"${urlEncode(oauthConsumerKey)}\",")
            if (!oauthToken.isNullOrBlank()) append("oauth_token=\"${urlEncode(oauthToken)}\",")
            append("oauth_signature_method=\"${urlEncode(oauthSignatureMethod.upperCase)}\",")
            append("oauth_timestamp=\"${urlEncode(oauthTimestamp)}\",")
            append("oauth_nonce=\"${urlEncode(oauthNonce)}\",")
            append("oauth_version=\"${urlEncode(oauthVersion)}\",")
            append("oauth_signature=\"${urlEncode(oauthSignature)}\"")
        }
    }

    private fun normalizeUrl() {
        val url = URL(url)
        if (!url.query.isNullOrBlank()) throw InternalServerError("URL should not contain queries")

        if (url.port == -1) return
        if (url.defaultPort != url.port) return
        else this.url = "${url.protocol}://${url.host}${url.path}"
    }

    private fun generateSignature(): String {
        val parameterString = generateParameterString()
        val baseString = buildString {
            append(httpMethod.name())
            append("&${urlEncode(url)}")
            append("&${urlEncode(parameterString)}")
        }
        val signingKey = "${urlEncode(oauthConsumerSecret)}&${urlEncode(oauthTokenSecret)}"

        val hash = hashingAlgorithm.hash(baseString, signingKey)
        return Base64.getMimeEncoder().encodeToString(hash)
    }

    private fun generateParameterString(): String {
        val parametersList: List<String> = buildList {
            add("oauth_consumer_key=${urlEncode(oauthConsumerKey)}")
            add("oauth_nonce=${urlEncode(oauthNonce)}")
            add("oauth_signature_method=${urlEncode(oauthSignatureMethod.upperCase)}")
            add("oauth_timestamp=${urlEncode(oauthTimestamp)}")
            add("oauth_version=${urlEncode(oauthVersion)}")
            if (!oauthToken.isNullOrBlank()) add("oauth_token=${urlEncode(oauthToken)}")
            addAll(parameters.map { (key, value) -> "${urlEncode(key)}=${urlEncode(value)}" })
            sort()
        }

        return parametersList.toString()
            .trim('[', ']')
            .replace(", ", "&")
    }

    private fun urlEncode(value: String?): String =
        urlEncoder.encode(value ?: "", Charset.forName("utf-8"))
}
