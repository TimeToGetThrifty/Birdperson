package com.greenback

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GreenbackApplication

fun main(args: Array<String>) {
    runApplication<GreenbackApplication>(*args)
}
