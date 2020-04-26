package org.integrational.canms.kotlin.domain.service

import org.integrational.canms.kotlin.domain.events.ChangeEventSender
import org.integrational.canms.kotlin.domain.events.DomainEntityCreated
import org.integrational.canms.kotlin.domain.events.DomainEntityDeleted
import org.integrational.canms.kotlin.domain.events.DomainEntityUpdated
import org.integrational.canms.kotlin.domain.model.DomainEntity
import org.integrational.canms.kotlin.domain.repo.DomainEntityRepo
import java.time.Instant
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Named
@Singleton
class ApplicationServiceImpl @Inject constructor(
    private val repo: DomainEntityRepo,
    private val sender: ChangeEventSender
) : ApplicationService {
    override fun addOrChangeDomainEntity(e: DomainEntity) =
        if (e.id == null) repo.create(e).also { sendCreated(it) }
        else repo.update(e).also { sendUpdated(e, it) }

    override fun removeDomainEntity(id: String) = repo.delete(id)?.also { sendDeleted(it) }

    override fun getDomainEntity(id: String) = repo.read(id)
    override fun getAllDomainEntities() = repo.readAll()

    private fun sendCreated(e: DomainEntity) {
        sender.send(DomainEntityCreated(Instant.now(), e))
    }

    private fun sendUpdated(before: DomainEntity, after: DomainEntity) {
        sender.send(DomainEntityUpdated(Instant.now(), before, after))
    }

    private fun sendDeleted(e: DomainEntity) {
        sender.send(DomainEntityDeleted(Instant.now(), e))
    }
}
