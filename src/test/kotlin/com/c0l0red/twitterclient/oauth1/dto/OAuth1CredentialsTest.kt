package com.c0l0red.twitterclient.oauth1.dto

import com.c0l0red.twitterclient.oauth1.algorithms.HashingAlgorithm
import org.apache.catalina.util.URLEncoder
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.http.HttpMethod
import java.nio.charset.Charset

class OAuth1CredentialsTest {
    private val oauthConsumerKey = "xvz1evFS4wEEPTGEFPHBog"
    private val oauthConsumerSecret = "kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw"
    private val oauthSignatureMethod = HashingAlgorithm.Method.HmacSHA1
    private val httpMethod = HttpMethod.POST
    private val url = "https://api.twitter.com/1.1/statuses/update.json"
    private val parameters = mapOf(
        Pair("include_entities", "true"),
        Pair("status", "Hello Ladies + Gentlemen, a signed OAuth request!")
    )
    private val oauthTimestamp = "1318622958"
    private val oauthVersion = "1.0"
    private val oauthNonce = "kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg"
    private val oauthToken = "370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb"
    private val oauthTokenSecret = "LswwdoUaIvS8ltyTt5jkRh4J50vUPVVHtR2YPi5kE"
    private val oauthSignatureWithOauthTokens = "hCtSmYh+iHYCEqBWrE7C7hYmtUk="
    private val oauthSignatureWithoutOauthTokens = "SeAnFOsJg0uDVE8Coxfv5QdLNII="

    private fun generateOauth1Credentials(withOauthTokens: Boolean = true) : OAuth1Credentials {
        return OAuth1Credentials(
            oauthSignatureMethod = oauthSignatureMethod,
            oauthConsumerKey = oauthConsumerKey,
            oauthConsumerSecret = oauthConsumerSecret,
            httpMethod = httpMethod,
            url = url,
            parameters = parameters,
            oauthTimestamp = oauthTimestamp,
            oauthVersion = oauthVersion,
            oauthNonce = oauthNonce,
            oauthToken = if (withOauthTokens) oauthToken else null,
            oauthTokenSecret = if (withOauthTokens) oauthTokenSecret else null,
        )
    }

    @Test
    fun asForm() {
        val correctMap = mapOf(
            Pair("oauth_consumer_key", oauthConsumerKey),
            Pair("oauth_token", oauthToken),
            Pair("oauth_signature_method", oauthSignatureMethod.upperCase),
            Pair("oauth_nonce", oauthNonce),
            Pair("oauth_version", oauthVersion),
            Pair("oauth_signature", oauthSignatureWithOauthTokens)
        )

        val credentials = generateOauth1Credentials()
        val resultingMap = credentials.asForm()

        assertEquals(correctMap, resultingMap)
    }

    @Test
    fun asHeaderString() {
        val correctHeaderString = "OAuth oauth_consumer_key=\"$oauthConsumerKey\"," +
                "oauth_token=\"$oauthToken\"," +
                "oauth_signature_method=\"${oauthSignatureMethod.upperCase}\"," +
                "oauth_timestamp=\"$oauthTimestamp\"," +
                "oauth_nonce=\"$oauthNonce\"," +
                "oauth_version=\"$oauthVersion\"," +
                "oauth_signature=\"${URLEncoder().encode(oauthSignatureWithOauthTokens, Charset.forName("utf-8"))}\""

        val credentials = generateOauth1Credentials()
        val resultingHeadersString = credentials.asHeaderString()

        assertEquals(correctHeaderString, resultingHeadersString)
    }

    @Test
    fun asForm_WithoutOauthTokens() {
        val correctMap = mapOf(
            Pair("oauth_consumer_key", oauthConsumerKey),
            Pair("oauth_signature_method", oauthSignatureMethod.upperCase),
            Pair("oauth_nonce", oauthNonce),
            Pair("oauth_version", oauthVersion),
            Pair("oauth_signature", oauthSignatureWithoutOauthTokens)
        )

        val credentials = generateOauth1Credentials(false)
        val resultingMap = credentials.asForm()

        assertEquals(correctMap, resultingMap)
    }

    @Test
    fun asHeaderString_WithoutOauthTokens() {
        val correctHeaderString = "OAuth oauth_consumer_key=\"$oauthConsumerKey\"," +
                "oauth_signature_method=\"${oauthSignatureMethod.upperCase}\"," +
                "oauth_timestamp=\"$oauthTimestamp\"," +
                "oauth_nonce=\"$oauthNonce\"," +
                "oauth_version=\"$oauthVersion\"," +
                "oauth_signature=\"${URLEncoder().encode(oauthSignatureWithoutOauthTokens, Charset.forName("utf-8"))}\""

        val credentials = generateOauth1Credentials(false)
        val resultingHeadersString = credentials.asHeaderString()

        assertEquals(correctHeaderString, resultingHeadersString)
    }
}