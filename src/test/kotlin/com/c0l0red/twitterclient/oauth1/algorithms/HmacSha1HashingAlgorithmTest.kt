package com.c0l0red.twitterclient.oauth1.algorithms

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class HmacSha1HashingAlgorithmTest {

    @Test
    fun smokeTest() {
        assertNotNull(HmacSha1HashingAlgorithm)
    }

    @Test
    fun hash() {
        val expectedResult: ByteArray = byteArrayOf(
            -115, -120, 28, 90, -97, 68, 28, -80, 7, 43, 110, -101, -30, -83, -101, 98, -61, 70, 16, -120
        )
        val result = HmacSha1HashingAlgorithm.hash("baseString", "secretKey")

        assertArrayEquals(expectedResult, result)
    }
}