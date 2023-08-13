package com.c0l0red.twitterclient.exceptions.payload

import java.util.*

open class ExceptionResponse(
    val timestamp: Date,
    val status: Int,
    val error: String,
    val message: String,
    val path: String,
)