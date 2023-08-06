package com.c0l0red.twitterclient.oauth1.services

import com.c0l0red.twitterclient.oauth1.algorithms.HashingAlgorithm
import com.c0l0red.twitterclient.oauth1.dto.OAuth1Credentials


class OAuthProviderImpl(
    override val signatureMethod: HashingAlgorithm.Method,
    override val consumerKey: String,
    override val consumerSecret: String,
    override val accessToken: String,
    override val tokenSecret: String,
    override val callBackUrl: String,
    override val verifier: String
) : OAuthProvider {

    override fun generateCredentials(): OAuth1Credentials {
        return OAuth1Credentials(
            signatureMethod,
            consumerKey,
            consumerSecret,
            accessToken,
            tokenSecret,
            callBackUrl,
            verifier
        )
    }

    override fun asForm(): Map<String, String> {
        TODO("Not yet implemented")
    }

    override fun asHeaderString(): String {
        TODO("Not yet implemented")
    }
}