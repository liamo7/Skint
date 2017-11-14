package com.loh.skint.domain.repository

import io.reactivex.Single
import java.util.*

interface Repository<E, D> {
    fun get(uuid: UUID): Single<D>
    fun getAll(): Single<MutableList<D>>
    fun add(model: E): Single<D>
    fun update(model: E): Single<D>
}