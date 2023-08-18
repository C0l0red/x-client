package com.c0l0red.twitterclient.oauth1.interceptors

import com.c0l0red.twitterclient.oauth1.dto.OAuth1Credentials
import com.c0l0red.twitterclient.oauth1.services.OAuthProviderImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*
import org.springframework.http.*
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpResponse
import java.net.URI

@ExtendWith(MockitoExtension::class)
class OAuth1HeadersInterceptorTest {
    private val request: HttpRequest = mock()
    private val mockExecution: ClientHttpRequestExecution = mock()
    private val mockResponse: ClientHttpResponse = mock()
    private val oAuth1Credentials: OAuth1Credentials = mock()

    private val oAuthProviderImpl: OAuthProviderImpl = mock()
    @InjectMocks
    lateinit var oAuth1HeadersInterceptor: OAuth1HeadersInterceptor

    @Test
    fun smokeTest() {
        assertNotNull(oAuth1HeadersInterceptor)
    }

    @Test
    fun intercept() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED
        val body = "name=test&age=10&bio=I am a test user!".toByteArray()

        whenever(oAuth1Credentials.asHeaderString()).thenReturn("credentials")
        whenever(oAuthProviderImpl.generateCredentials(isA<HttpMethod>(), isA<String>(), isA<Map<String, String>>()))
            .thenReturn(oAuth1Credentials)
        whenever(mockExecution.execute(isA<HttpRequest>(), isA<ByteArray>())).thenReturn(mockResponse)

        whenever(request.headers).thenReturn(headers)
        whenever(request.uri).thenReturn(URI("http://test.com?key=value&order=1"))
        whenever(request.method).thenReturn(HttpMethod.POST)

        oAuth1HeadersInterceptor.intercept(request, body, mockExecution)

        assertEquals("credentials", request.headers.getFirst(HttpHeaders.AUTHORIZATION))
        verify(oAuth1Credentials, times(1)).asHeaderString()
        verify(oAuthProviderImpl, times(1))
            .generateCredentials(isA<HttpMethod>(), isA<String>(), argWhere {
                it["name"] == "test" && it["key"] == "value" && it["bio"] == "I am a test user!"
            })
        verify(mockExecution, times(1)).execute(isA<HttpRequest>(), isA<ByteArray>())
    }

    @Test
    fun intercept_SkipsPublicPaths() {
        whenever(request.uri).thenReturn(URI("http://test.com/oauth/authenticate?token=token"))
        whenever(mockExecution.execute(isA<HttpRequest>(), isA<ByteArray>())).thenReturn(mockResponse)

        oAuth1HeadersInterceptor.intercept(request, byteArrayOf(), mockExecution)

        verifyNoInteractions(oAuth1Credentials)
        verifyNoInteractions(oAuthProviderImpl)
        verify(mockExecution, times(1)).execute(request, byteArrayOf())
    }
}