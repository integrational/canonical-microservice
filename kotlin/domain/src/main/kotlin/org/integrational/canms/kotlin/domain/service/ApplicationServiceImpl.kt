package org.integrational.canms.kotlin.domain.service

import org.integrational.canms.kotlin.domain.model.DomainEntity
import org.integrational.canms.kotlin.domain.repo.DomainEntityRepo

class ApplicationServiceImpl(private val repo: DomainEntityRepo) : ApplicationService {
    override fun addOrUpdateDomainEntity(e: DomainEntity) = repo.createOrUpdate(e)
    override fun getDomainEntity(id: String) = repo.read(id)
    override fun getAllDomainEntities() = repo.readAll()
    override fun deleteDomainEntity(id: String) = repo.delete(id)
}