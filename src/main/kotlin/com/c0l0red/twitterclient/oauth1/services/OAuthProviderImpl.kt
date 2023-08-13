package com.c0l0red.twitterclient.oauth1.services

import com.c0l0red.twitterclient.oauth1.algorithms.HashingAlgorithm
import com.c0l0red.twitterclient.oauth1.dto.OAuth1Credentials
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod


class OAuthProviderImpl(
    @Value("\${twitter.oauth1.0.consumer_key}") override val consumerKey: String,
    @Value("\${twitter.oauth1.0.consumer_secret}") override val consumerSecret: String,
    @Value("\${twitter.oauth1.0.callback_url}") override val callBackUrl: String,
    override val verifier: String
) : OAuthProvider {
    override val signatureMethod: HashingAlgorithm.Method = HashingAlgorithm.Method.HmacSHA1

    override fun generateCredentials(httpMethod: HttpMethod, urlPath: String, parameters: Map<String, String>): OAuth1Credentials {
        return OAuth1Credentials(
            signatureMethod,
            consumerKey,
            consumerSecret,
            httpMethod,
            urlPath,
            parameters
        )
    }
}