package com.loh.skint.domain.repository

import io.reactivex.Single

interface Repository<E, T> {
    fun get(id: Int): Single<T>
    fun getAll(): Single<List<T>>
    fun add(model: E): Single<T>
    fun update(model: E): Single<T>
}