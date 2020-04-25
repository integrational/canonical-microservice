package org.integrational.canms.kotlin.domain.service

import org.integrational.canms.kotlin.domain.model.DomainEntity

/**
 * Interface to the application service exposed by this domain.
 */
interface ApplicationService {

    fun addOrUpdateDomainEntity(e: DomainEntity): DomainEntity

    fun getDomainEntity(id: String): DomainEntity?

    fun deleteDomainEntity(id: String): DomainEntity?
}