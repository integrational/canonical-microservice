package org.integrational.canms.kotlin.springcloud.repo.inmemory

import org.integrational.canms.kotlin.domain.model.DomainEntity
import org.integrational.canms.kotlin.domain.repo.DomainEntityRepo
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

// TODO synchronize
@Named
@Singleton
class DomainEntityInMemoryRepo : DomainEntityRepo {
    private val db = mutableMapOf<String, DomainEntity>()

    override fun create(e: DomainEntity) = require(e.id == null).let {
        nextID().let { id -> (e.copy(id = id)).also { db[id] = it } }
    }

    private fun nextID() = UUID.randomUUID().toString()
    override fun read(id: String) = db[id]
    override fun readAll() = db.values.toList()
    override fun update(e: DomainEntity) = requireNotNull(e.id).also { requireNotNull(read(it)) }.let { id ->
        e.also { db[id] = e }
    }

    override fun delete(id: String) = db[id].also { db -= id }
}