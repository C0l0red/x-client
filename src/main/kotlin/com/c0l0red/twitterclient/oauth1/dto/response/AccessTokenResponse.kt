package com.c0l0red.twitterclient.oauth1.dto.response

data class AccessTokenResponse(
    override val oauthToken: String,
    private val oauthTokenSecret: String,
    private val userId: String,
    private val screenName: String,
): OAuthTokenResponse(oauthToken)
