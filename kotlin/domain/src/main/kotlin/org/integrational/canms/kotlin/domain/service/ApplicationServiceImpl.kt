package org.integrational.canms.kotlin.domain.service

import org.integrational.canms.kotlin.domain.model.DomainEntity
import org.integrational.canms.kotlin.domain.repo.DomainEntityRepo
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Named
@Singleton
class ApplicationServiceImpl @Inject constructor(private val repo: DomainEntityRepo) : ApplicationService {
    override fun addOrUpdateDomainEntity(e: DomainEntity) = repo.createOrUpdate(e)
    override fun getDomainEntity(id: String) = repo.read(id)
    override fun getAllDomainEntities() = repo.readAll()
    override fun deleteDomainEntity(id: String) = repo.delete(id)
}