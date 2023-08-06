package com.c0l0red.twitterclient.user.services

import com.c0l0red.twitterclient.user.models.User

interface UserService {
    fun createUser()

    fun findUserById(userId: String): User
}