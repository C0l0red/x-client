package com.c0l0red.twitterclient.oauth1.services

import com.c0l0red.twitterclient.oauth1.algorithms.HashingAlgorithm
import com.c0l0red.twitterclient.oauth1.dto.OAuth1Credentials

interface OAuthProvider {
    val signatureMethod: HashingAlgorithm.Method;
    val consumerKey: String;
    val consumerSecret: String;
    val accessToken: String;
    val tokenSecret: String;
    val callBackUrl: String;
    val verifier: String;

    fun generateCredentials(): OAuth1Credentials
    fun asForm(): Map<String, String>

    fun asHeaderString(): String
}