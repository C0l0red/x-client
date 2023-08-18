package com.c0l0red.twitterclient.utils

private val alphanumericChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')

fun generateAlphanumericString(length: Int): String {
    return buildString {
        repeat(length) { append(alphanumericChars.random()) }
    }
}