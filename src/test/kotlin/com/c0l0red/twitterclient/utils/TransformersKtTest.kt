package com.c0l0red.twitterclient.utils
import org.junit.jupiter.api.Assertions.assertEquals

import org.junit.jupiter.api.Test
import java.net.URL

class TransformersKtTest {

    @Test
    fun queryToMap() {
        val url = URL("http://test.com?name=John&age=20")
        val resultantMap = queryToMap(url)

        val expectedMap = mapOf(
            "name" to "John",
            "age" to "20"
        )
        assertEquals(expectedMap, resultantMap)
    }

    @Test
    fun queryToMap_WithEmptyQuery() {
        val url = URL("http://test.com")
        val resultantMap = queryToMap(url)

        assertEquals(mapOf<String, String>(), resultantMap)
    }

    @Test
    fun formToMap() {
        val formString = "firstname=John&lastname=Doe"
        val resultantMap = formToMap(formString)

        val expectedMap = mapOf(
            "firstname" to "John",
            "lastname" to "Doe"
        )
        assertEquals(expectedMap, resultantMap)
    }

    @Test
    fun formToMap_WithEmptyForm() {
        val formString = ""
        val resultantMap = formToMap(formString)

        assertEquals(mapOf<String, String>(), resultantMap)
    }
}