package org.integrational.canms.util.loggingeventsconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import java.util.logging.Logger

@SpringBootApplication
@EnableBinding(Sink::class)
class App {
    private val log = Logger.getLogger(App::class.java.canonicalName)

    init {
        log.info("Got all dependencies!")
    }

    @StreamListener(Sink.INPUT)
    fun handleEvent(event: Any) {
        log.info("Received event: $event")
    }
}

fun main(args: Array<String>) {
    runApplication<App>(*args)
}
