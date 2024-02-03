package com.punch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PunchApplication

fun main(args: Array<String>) {
    runApplication<PunchApplication>(*args)
}
