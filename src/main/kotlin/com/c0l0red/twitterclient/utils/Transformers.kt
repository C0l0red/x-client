package com.c0l0red.twitterclient.utils

import java.io.UnsupportedEncodingException
import java.net.URL
import java.net.URLDecoder


@Throws(UnsupportedEncodingException::class)
fun queryToMap(url: URL): MutableMap<String, String> {
    val query: String? = url.query

    return urlEncodedPairsToMap(query)
}

fun formToMap(formString: String): MutableMap<String, String> {
    return urlEncodedPairsToMap(formString)
}

private fun urlEncodedPairsToMap(pairsString: String?) : MutableMap<String, String> {
    val pairsMap = linkedMapOf<String, String>()

    pairsString?.split("&")
        ?.dropLastWhile { it.isEmpty() }
        ?.forEach {
            val index = it.indexOf("=")
            pairsMap[URLDecoder.decode(it.substring(0, index), "UTF-8")] =
                URLDecoder.decode(it.substring(index + 1), "UTF-8")
        }

    return pairsMap
}
