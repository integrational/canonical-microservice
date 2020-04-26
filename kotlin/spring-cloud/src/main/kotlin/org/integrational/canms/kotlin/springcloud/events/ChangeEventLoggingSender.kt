package org.integrational.canms.kotlin.springcloud.events

import org.integrational.canms.kotlin.domain.events.ChangeEventSender
import org.integrational.canms.kotlin.domain.events.DomainEntityChangeEvent
import java.util.logging.Logger
import javax.inject.Named
import javax.inject.Singleton

@Named
@Singleton
class ChangeEventLoggingSender : ChangeEventSender {
    private val log = Logger.getLogger(this::class.qualifiedName)

    override fun send(event: DomainEntityChangeEvent) {
        log.info("Sending DomainEntityChangeEvent: $event")
    }
}