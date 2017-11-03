package com.loh.skint.domain.repository

import io.reactivex.Single
import java.util.*

interface Repository<T> {
    fun get(uuid: UUID): Single<T>
    fun getAll(): Single<List<T>>
    fun add(model: T): Single<T>
    fun update(model: T): Single<T>
}