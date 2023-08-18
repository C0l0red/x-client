package com.c0l0red.twitterclient.oauth1.interceptors

import com.c0l0red.twitterclient.oauth1.services.OAuthProvider
import com.c0l0red.twitterclient.utils.formToMap
import com.c0l0red.twitterclient.utils.queryToMap
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpRequest
import org.springframework.http.MediaType
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Component
import java.net.URL

@Component
class OAuth1HeadersInterceptor(
    private val oAuthProvider: OAuthProvider
): ClientHttpRequestInterceptor {

    private val publicPaths = listOf(
        "/oauth/authenticate",
        "/oauth/authorize",
        "/oauth/access_token",
    )

    override fun intercept(
        request: HttpRequest,
        body: ByteArray,
        execution: ClientHttpRequestExecution
    ): ClientHttpResponse {
        val url: URL = request.uri.toURL()

        if (!isPublicPath(url)) {
            val method: HttpMethod = request.method
            val parameters = getParameters(request, body)

            val headersString = oAuthProvider.generateCredentials(
                method,
                url.toString(),
                parameters,
            ).asHeaderString()
            request.headers.set(HttpHeaders.AUTHORIZATION, headersString)
        }
        return execution.execute(request, body)
    }

    private fun isPublicPath(url: URL): Boolean = publicPaths.contains(url.path)

    private fun getParameters(request: HttpRequest, body: ByteArray): Map<String, String> {
        val parameters = queryToMap(request.uri.toURL())

        if (
            (request.method != HttpMethod.GET)
            &&
            (MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(request.headers.contentType))
        ) {
            val formString = body.decodeToString()
            parameters.putAll(formToMap(formString))
        }
        return parameters
    }
}