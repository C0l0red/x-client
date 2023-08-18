package com.c0l0red.twitterclient.oauth1.services

import com.c0l0red.twitterclient.oauth1.dto.request.AccessTokenRequest
import com.c0l0red.twitterclient.oauth1.dto.request.AuthenticateRequest
import com.c0l0red.twitterclient.oauth1.dto.request.CreateRequestToken
import com.c0l0red.twitterclient.oauth1.dto.response.AccessTokenResponse
import com.c0l0red.twitterclient.oauth1.dto.response.RequestTokenResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class OAuthServiceImpl(
    @Qualifier("OAuth 1.0") private val restTemplate: RestTemplate
) : OAuthService {

    override fun requestToken(createRequestToken: CreateRequestToken): RequestTokenResponse {
        TODO("Not yet implemented")
    }

    override fun authenticateUser(authenticateRequest: AuthenticateRequest) {
        TODO("Not yet implemented")
    }

    override fun getAccessToken(accessTokenRequest: AccessTokenRequest): AccessTokenResponse {
        TODO("Not yet implemented")
    }

}