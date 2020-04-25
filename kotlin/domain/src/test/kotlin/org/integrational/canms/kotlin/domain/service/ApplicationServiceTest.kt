package org.integrational.canms.kotlin.domain.service

import org.integrational.canms.kotlin.domain.model.DomainEntity
import org.integrational.canms.kotlin.domain.repo.DomainEntityRepo
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class ApplicationServiceTest {

    class Repo : DomainEntityRepo {
        private val db = mutableMapOf<String, DomainEntity>()

        override fun create(e: DomainEntity) =
            if (e.id != null) throw IllegalArgumentException("id is not null") else {
                val id = UUID.randomUUID().toString()
                val pe = e.copy(id = id)
                db[id] = pe
                pe
            }

        override fun read(id: String) = TODO()

        override fun readAll(): List<DomainEntity> {
            TODO("Not yet implemented")
        }

        override fun update(e: DomainEntity): DomainEntity {
            TODO("Not yet implemented")
        }

        override fun delete(id: String): DomainEntity? {
            TODO("Not yet implemented")
        }

    }

    private val repo = Repo()
    private val asvc = ApplicationServiceImpl(repo)

    @Test
    fun `create happy path`() {
        val e1 = DomainEntity(id = null, name = "name", children = listOf())
        val e2 = asvc.addOrUpdateDomainEntity(e1)
        val e3 = e2.copy(id = null)
        assertNotNull(e2.id)
        assertEquals(e1, e3)
    }
}