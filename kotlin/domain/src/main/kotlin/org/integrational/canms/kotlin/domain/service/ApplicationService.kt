package org.integrational.canms.kotlin.domain.service

import org.integrational.canms.kotlin.domain.model.DomainEntity

/**
 * Interface to the application service exposed by this domain.
 */
interface ApplicationService {
    fun addOrChangeDomainEntity(e: DomainEntity): DomainEntity
    fun removeDomainEntity(id: String): DomainEntity?
    fun getDomainEntity(id: String): DomainEntity?
    fun getAllDomainEntities(): List<DomainEntity>
}