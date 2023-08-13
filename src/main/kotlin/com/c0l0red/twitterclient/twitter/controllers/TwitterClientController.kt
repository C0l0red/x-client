package com.c0l0red.twitterclient.twitter.controllers

import com.c0l0red.twitterclient.oauth1.services.OAuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController("twitter/auth")
class TwitterClientController(
    private val oAuthService: OAuthService,
) {
    @PostMapping("request-token")
    fun requestToken() {}

    @PostMapping("authenticate")
    fun authenticateUser() {}

    @PostMapping("access-token")
    fun getAccessToken() {}
}