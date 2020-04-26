package org.integrational.canms.kotlin.springcloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CanmsApplication

fun main(args: Array<String>) {
	runApplication<CanmsApplication>(*args)
}
