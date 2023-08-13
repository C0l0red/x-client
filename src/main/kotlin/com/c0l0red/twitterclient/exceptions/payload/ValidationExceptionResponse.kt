package com.donah.simplixchange.exceptions.payload

import java.util.Date

class ValidationExceptionResponse(
    timestamp: Date,
    status: Int,
    error: String,
    message: String = "Validation Error",
    val validationErrors: List<String>,
    path: String,
) : ExceptionResponse(timestamp, status, error, message, path)