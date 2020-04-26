package org.integrational.canms.kotlin.domain.repo

import org.integrational.canms.kotlin.domain.model.DomainEntity

/**
 * Interface to a repository of persistent [DomainEntity] instances.
 */
interface DomainEntityRepo {
    /**
     * Create a persistent representation of the given [DomainEntity], assigning it a new unique [DomainEntity.id].
     * The `id` of the given [DomainEntity] [e] must therefore be `null` or an [IllegalArgumentException] is thrown.
     * Returns a new [DomainEntity] instance representing the persisted state, including the assigned id.
     *
     * @param e The [DomainEntity] to persist, with `null` `id`.
     * @return The persisted [DomainEntity], including the assigned non-`null` `id`.
     * @throws [IllegalArgumentException] If the `id` of the given [DomainEntity] was not `null`
     */
    fun create(e: DomainEntity): DomainEntity

    /**
     * Update a persistent representation of the given [DomainEntity], identified by its unique [DomainEntity.id].
     * The `id` of the given [DomainEntity] [e] must therefore be non-`null` or an [IllegalArgumentException] is thrown.
     * Returns the given [DomainEntity] unchanged, which now represents its persisted state.
     *
     * @param e The [DomainEntity] to update, with non-`null` `id`.
     * @return [e].
     * @throws [IllegalArgumentException] If the `id` of the given [DomainEntity] was `null`
     */
    fun update(e: DomainEntity): DomainEntity

    /**
     * Create or update the persistent representation of the given [DomainEntity], depending on its unique [DomainEntity.id].
     */
    fun createOrUpdate(e: DomainEntity) = if (e.id == null) create(e) else update(e)

    /** Delete and return the persistent representation of the [DomainEntity] with the given [id], or `null`. */
    fun delete(id: String): DomainEntity?

    /** Delete and return the persistent representation of the given [DomainEntity], or `null`. */
    fun delete(e: DomainEntity) = e.id?.let { delete(it) }

    /** Read and return the persistent representation of the [DomainEntity] with the given [id], or `null`. */
    fun read(id: String): DomainEntity?

    /** Rad and return the persistent representations of all known [DomainEntity] instances. */
    fun readAll(): List<DomainEntity>
}