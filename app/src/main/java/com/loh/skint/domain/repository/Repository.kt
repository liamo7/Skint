package com.loh.skint.domain.repository

import io.reactivex.Single
import java.util.*

interface Repository<E, T> {
    fun get(uuid: UUID): Single<T>
    fun getAll(): Single<List<T>>
    fun add(model: E): Single<T>
    fun update(model: E): Single<T>
}