package org.integrational.canms.kotlin.domain.model

/**
 * An immutable domain entity with an optional [id], aggregating a list of [DomainValueObject] instances.
 */
data class DomainEntity(val id: String?, val name: String, val children: List<DomainValueObject>)

/**
 * An immutable value object owned by a [DomainEntity].
 */
data class DomainValueObject(val desc: String, val value: Int)