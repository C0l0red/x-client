package com.c0l0red.twitterclient.oauth1.services

import com.c0l0red.twitterclient.oauth1.dto.request.AccessTokenRequest
import com.c0l0red.twitterclient.oauth1.dto.request.AuthenticateRequest
import com.c0l0red.twitterclient.oauth1.dto.request.CreateRequestToken
import com.c0l0red.twitterclient.oauth1.dto.response.AccessTokenResponse
import com.c0l0red.twitterclient.oauth1.dto.response.RequestTokenResponse

interface OAuthService {
    val callBackUrl: String

    fun requestToken(createRequestToken: CreateRequestToken): RequestTokenResponse

    fun authenticateUser(authenticateRequest: AuthenticateRequest)

    fun getAccessToken(accessTokenRequest: AccessTokenRequest): AccessTokenResponse
}