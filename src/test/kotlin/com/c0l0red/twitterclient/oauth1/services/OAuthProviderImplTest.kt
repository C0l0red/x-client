package com.c0l0red.twitterclient.oauth1.services

import org.bson.assertions.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod

class OAuthProviderImplTest {

    private val oAuthProviderImpl: OAuthProvider =  OAuthProviderImpl(
        "consumerKey",
        "consumerSecret",
    )

    @Test
    fun smokeTest() {
        assertNotNull(oAuthProviderImpl)
    }

    @Test
    fun generateCredentials() {
        val oAuth1Credentials = oAuthProviderImpl
            .generateCredentials(HttpMethod.GET, "https://test.com", mapOf())

        assertNotNull(oAuth1Credentials)
    }
}