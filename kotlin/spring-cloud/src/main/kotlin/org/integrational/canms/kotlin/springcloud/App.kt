package org.integrational.canms.kotlin.springcloud

import org.integrational.canms.kotlin.domain.service.ApplicationService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.logging.Logger
import javax.inject.Inject

@SpringBootApplication(scanBasePackageClasses = [App::class, ApplicationService::class])
class App @Inject constructor(private val asvc: ApplicationService) {
    val log = Logger.getLogger(App::class.java.canonicalName)

    init {
        log.info("Got all dependencies!")
    }
}

fun main(args: Array<String>) {
    runApplication<App>(*args)
}
