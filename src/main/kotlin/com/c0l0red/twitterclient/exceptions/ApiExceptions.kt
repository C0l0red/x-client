package com.c0l0red.twitterclient.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class InternalServerError(message: String): ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message)
class BadRequestException(message: String = "Bad Request") : ResponseStatusException(HttpStatus.BAD_REQUEST, message)
class DuplicateRecordException(message: String) : ResponseStatusException(HttpStatus.CONFLICT, message)
class UpgradeRequiredException(message: String) : ResponseStatusException(HttpStatus.UPGRADE_REQUIRED, message)
class UserNotFoundException(message: String = "User Not Found") : ResponseStatusException(HttpStatus.NOT_FOUND, message)
class ValidationException(val errors: List<String>) : ResponseStatusException(HttpStatus.BAD_REQUEST)