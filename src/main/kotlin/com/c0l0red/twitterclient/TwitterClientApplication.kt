package com.c0l0red.twitterclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TwitterClientApplication

fun main(args: Array<String>) {
    runApplication<TwitterClientApplication>(*args)
}
