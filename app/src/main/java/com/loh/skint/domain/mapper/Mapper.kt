package com.loh.skint.domain.mapper

interface Mapper<E, S, U> {

    fun mapEntityToDomain(entity: E): S

    fun mapDomainToEntity(domain: S): E

    fun mapDomainToUi(domain: S): U

    fun mapEntityToDomain(entity: List<E>): List<S> {
        val list = ArrayList<S>()
        entity.mapTo(list) { mapEntityToDomain(it) }
        return list
    }

    fun mapDomainToEntity(domain: List<S>): List<E> {
        val list = ArrayList<E>()
        domain.mapTo(list) { mapDomainToEntity(it) }
        return list
    }

    fun mapDomainToUi(domain: List<S>): List<U> {
        val list = ArrayList<U>()
        domain.mapTo(list) { mapDomainToUi(it) }
        return list
    }

}