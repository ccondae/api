package org.ccondaeapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class CcondaeApiApplication

fun main(args: Array<String>) {
    runApplication<CcondaeApiApplication>(*args)
}
