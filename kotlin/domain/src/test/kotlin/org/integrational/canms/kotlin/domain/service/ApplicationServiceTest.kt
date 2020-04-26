package org.integrational.canms.kotlin.domain.service

import org.integrational.canms.kotlin.domain.events.ChangeEventSender
import org.integrational.canms.kotlin.domain.events.DomainEntityChangeEvent
import org.integrational.canms.kotlin.domain.model.DomainEntity
import org.integrational.canms.kotlin.domain.model.DomainValueObject
import org.integrational.canms.kotlin.domain.repo.DomainEntityRepo
import java.util.*
import kotlin.test.*


class ApplicationServiceTest {

    // TODO test side-effects on [ChangeEventSender]
    private class Sender : ChangeEventSender {
        override fun send(event: DomainEntityChangeEvent) {}
    }

    private class Repo : DomainEntityRepo {
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

    private lateinit var asvc: ApplicationService
    private val de = DomainEntity(
        id = null, name = "name", children = listOf(
            DomainValueObject("desc1", 1),
            DomainValueObject("desc2", 2)
        )
    )

    @BeforeTest
    fun init() {
        asvc = ApplicationServiceImpl(Repo(), Sender())
    }

    private fun assertSize(size: Int) {
        assertEquals(size, asvc.getAllDomainEntities().size)
    }

    @Test
    fun `create happy path`() {
        assertSize(0)
        val e1 = asvc.addOrChangeDomainEntity(de)
        assertSize(1)
        assertNotNull(e1.id)
        assertEquals(de.children[1], e1.children[1])
        assertEquals(de, e1.copy(id = null))
    }

    @Test
    fun `update happy path`() {
        assertSize(0)
        val e1 = asvc.addOrChangeDomainEntity(de)
        assertSize(1)
        val e2 = e1.copy(name = "new name")
        val e3 = asvc.addOrChangeDomainEntity(e2)
        assertSize(1)
        assertEquals(e2, e3)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `update non-existent throws IAE`() {
        asvc.addOrChangeDomainEntity(de.copy(id = "non-existent"))
    }

    @Test
    fun `get non-existent returns null`() {
        assertNull(asvc.getDomainEntity("non-existent"))
        asvc.addOrChangeDomainEntity(de)
        assertNull(asvc.getDomainEntity("non-existent"))
    }

    @Test
    fun `getAll always returns list`() {
        assertSize(0)
        assertNotNull(asvc.getAllDomainEntities())
        val e1 = asvc.addOrChangeDomainEntity(de)
        assertSize(1)
        assertEquals(listOf(e1), asvc.getAllDomainEntities())
    }

    @Test
    fun `delete non-existent and existent`() {
        assertSize(0)
        assertNull(asvc.removeDomainEntity("non-existent"))
        assertSize(0)
        val e2 = asvc.addOrChangeDomainEntity(de)
        assertSize(1)
        assertNull(asvc.removeDomainEntity("non-existent"))
        assertSize(1)
        assertEquals(e2, asvc.removeDomainEntity(e2.id!!))
        assertSize(0)
    }
}