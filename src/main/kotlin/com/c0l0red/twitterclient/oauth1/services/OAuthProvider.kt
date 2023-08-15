package com.c0l0red.twitterclient.oauth1.services

import com.c0l0red.twitterclient.oauth1.algorithms.HashingAlgorithm
import com.c0l0red.twitterclient.oauth1.dto.OAuth1Credentials
import org.springframework.http.HttpMethod

interface OAuthProvider {
    val signatureMethod: HashingAlgorithm.Method
    val consumerKey: String
    val consumerSecret: String
    val callBackUrl: String

    fun generateCredentials(
        httpMethod: HttpMethod,
        urlPath: String,
        parameters: Map<String, String>
    ): OAuth1Credentials
}