package org.integrational.canms.kotlin.domain.events

import org.integrational.canms.kotlin.domain.model.DomainEntity
import java.time.Instant

/**
 * Interface for sending entity change events for [DomainEntity].
 */
interface ChangeEventSender {
    fun send(event: DomainEntityChangeEvent)
}

sealed class ChangeEvent {
    abstract val at: Instant
}

sealed class DomainEntityChangeEvent : ChangeEvent()

data class DomainEntityCreated(
    override val at: Instant, val after: DomainEntity
) : DomainEntityChangeEvent()

data class DomainEntityUpdated(
    override val at: Instant, val before: DomainEntity, val after: DomainEntity
) : DomainEntityChangeEvent()

data class DomainEntityDeleted(
    override val at: Instant, val before: DomainEntity
) : DomainEntityChangeEvent()
