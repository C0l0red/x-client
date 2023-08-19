package com.c0l0red.twitterclient.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class StringsKtTest {

    @Test
    fun testGenerateAlphanumericString() {
        val firstString = generateAlphanumericString(15)
        val secondString = generateAlphanumericString(15)

        assertNotEquals(firstString, secondString)
    }

    @Test
    fun testBuildUrl_WithHost() {
        val host = "http://www.test.com"
        val path = "/users/1"
        val queryPairs = arrayOf(
            "name" to "John",
            "order" to "age"
        )

        val url = buildUrl(host, path, *queryPairs)
        assertEquals("http://www.test.com/users/1?name=John&order=age", url)
    }

    @Test
    fun testBuildUrl_WithoutHost() {
        val path = "/books/2"
        val queryPairs = arrayOf(
            "genre" to "horror"
        )

        val url = buildUrl(path, *queryPairs)

        assertEquals("/books/2?genre=horror", url)
    }

    @Test
    fun testBuildUrl_WithoutQueryParams() {
        val path = "/api/v1/login"
        val queryPairs = arrayOf<Pair<String, String>>()

        val url = buildUrl(path, *queryPairs)

        assertEquals("/api/v1/login", url)
    }
}