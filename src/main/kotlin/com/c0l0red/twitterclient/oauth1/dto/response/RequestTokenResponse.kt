package com.c0l0red.twitterclient.oauth1.dto.response

data class RequestTokenResponse(
    private val oauthTokenSecret: String,
    private val oauthCallbackConfirmed: Boolean,
    override val oauthToken: String,
): OAuthTokenResponse(oauthToken)
