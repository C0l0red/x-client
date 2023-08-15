package com.c0l0red.twitterclient.exceptions.payload

import java.util.*

class ValidationExceptionResponse(
    timestamp: Date,
    status: Int,
    error: String,
    message: String = "Validation Error",
    val validationErrors: List<String>,
    path: String,
) : ExceptionResponse(timestamp, status, error, message, path)