package com.loh.skint.data.repository

import com.loh.skint.data.entity.Account
import com.loh.skint.domain.repository.AccountRepository
import io.reactivex.Single
import io.requery.Persistable
import io.requery.reactivex.KotlinReactiveEntityStore
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepository @Inject constructor(private val dataStore: KotlinReactiveEntityStore<Persistable>) : AccountRepository {

    override fun update(model: Account): Single<Account> {
        return dataStore.update(model)
    }

    override fun add(model: Account): Single<Account> {
        return dataStore.insert(model)
    }

    override fun get(uuid: UUID): Single<Account> {
        return dataStore.select(Account::class)
                .get().observable().singleOrError()
    }

    override fun getAll(): Single<List<Account>> {
        return dataStore.select(Account::class)
                .get().observable().toList()
    }
}