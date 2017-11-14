package com.loh.skint.data.repository

import com.loh.skint.data.entity.Account
import com.loh.skint.data.entity.AccountEntity
import com.loh.skint.domain.mapper.AccountMapper
import com.loh.skint.domain.repository.AccountRepository
import io.reactivex.Single
import io.requery.Persistable
import io.requery.reactivex.KotlinReactiveEntityStore
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepository @Inject constructor(private val dataStore: KotlinReactiveEntityStore<Persistable>,
                                            private val mapper: AccountMapper)
    : AccountRepository {

    override fun update(model: Account): Single<com.loh.skint.domain.model.Account> {
        return dataStore.upsert(model)
                .map { mapper.mapEntityToDomain(it) }
    }

    override fun add(model: Account): Single<com.loh.skint.domain.model.Account> {
        return dataStore.insert(model)
                .map { mapper.mapEntityToDomain(it) }
    }

    override fun get(uuid: UUID): Single<com.loh.skint.domain.model.Account> {
        return dataStore.select(Account::class)
                .where(AccountEntity.UUID.eq(uuid))
                .get().observable().singleOrError()
                .map { mapper.mapEntityToDomain(it) }
    }

    override fun getAll(): Single<MutableList<com.loh.skint.domain.model.Account>> {
        return dataStore.select(Account::class)
                .get().observable().toList()
                .map { mapper.mapEntityToDomain(it) }
    }

    override fun getBlocking(uuid: UUID): Account {
        return dataStore.select(AccountEntity::class)
                .where(AccountEntity.UUID.eq(uuid))
                .get().first()
    }
}