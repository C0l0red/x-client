package com.c0l0red.twitterclient.utils

private val alphanumericChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')

fun generateAlphanumericString(length: Int): String {
    return buildString {
        repeat(length) { append(alphanumericChars.random()) }
    }
}

fun buildUrl(host: String, path: String, vararg queryParams: Pair<String, String>): String {
    val queryString =
        if (queryParams.isEmpty())
            ""
        else buildString {
            append("?")
            queryParams.take(queryParams.size - 1)
                .forEach{ append("${it.first}=${it.second}&")}

            val pair = queryParams[queryParams.size - 1]
            append("${pair.first}=${pair.second}")
        }

    return "$host$path$queryString"
}

fun buildUrl(path: String, vararg queryPairs: Pair<String, String>): String {
     return buildUrl("", path, queryParams = queryPairs)
}