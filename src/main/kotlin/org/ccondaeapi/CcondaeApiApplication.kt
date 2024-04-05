package org.ccondaeapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CcondaeApiApplication

fun main(args: Array<String>) {
    runApplication<CcondaeApiApplication>(*args)
}
