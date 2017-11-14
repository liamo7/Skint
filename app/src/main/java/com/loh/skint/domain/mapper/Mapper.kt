package com.loh.skint.domain.mapper

interface Mapper<E, S> {

    fun mapEntityToDomain(entity: E): S

    fun mapDomainToEntity(domain: S): E

    fun mapEntityToDomain(entity: MutableList<E>): MutableList<S> {
        val list = mutableListOf<S>()
        entity.mapTo(list) { mapEntityToDomain(it) }
        return list
    }

    fun mapDomainToEntity(domain: MutableList<S>): MutableList<E> {
        val list = mutableListOf<E>()
        domain.mapTo(list) { mapDomainToEntity(it) }
        return list
    }
}