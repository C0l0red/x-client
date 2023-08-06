package com.c0l0red.twitterclient.user.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("users")
data class User(
    @Id
    val id: String = ObjectId().toHexString(),

    @Indexed(unique = true)
    val twitterUserId: String,

    val twitterUsername: String,

    val token: String? = null,
    val tokenSecret: String? = null,
)
