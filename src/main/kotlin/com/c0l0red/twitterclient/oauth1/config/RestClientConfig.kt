package com.c0l0red.twitterclient.oauth1.config

import com.c0l0red.twitterclient.oauth1.interceptors.OAuth1HeadersInterceptor
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.DefaultUriBuilderFactory


@Configuration
class RestClientConfig(
    private val oAuth1HeadersInterceptor: OAuth1HeadersInterceptor
) {
    @Bean
    @Qualifier("OAuth 1.0")
    fun restTemplate(): RestTemplate {
        val restTemplate = RestTemplate()

        val interceptors = restTemplate.interceptors
        interceptors.add(oAuth1HeadersInterceptor)

        restTemplate.interceptors = interceptors
        restTemplate.uriTemplateHandler = DefaultUriBuilderFactory("https://api.twitter.com")

        return restTemplate
    }
}