package com.c0l0red.twitterclient.oauth1.dto.request

data class AccessTokenRequest(private val oAuthToken: String, private val oAuthVerifier: String)