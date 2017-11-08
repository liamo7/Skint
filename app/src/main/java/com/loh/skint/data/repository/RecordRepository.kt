package com.loh.skint.data.repository

import com.loh.skint.data.entity.RecordEntity
import com.loh.skint.domain.mapper.RecordMapper
import com.loh.skint.domain.model.Record
import com.loh.skint.domain.repository.RecordRepository
import com.loh.skint.util.timespan
import io.reactivex.Single
import io.requery.Persistable
import io.requery.reactivex.KotlinReactiveEntityStore
import org.threeten.bp.LocalDate
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RecordRepository @Inject constructor(private val dataStore: KotlinReactiveEntityStore<Persistable>, private val mapper: RecordMapper) : RecordRepository {

    override fun getRecentRecords(accountId: Int, date: LocalDate): Single<List<Record>> {
        return dataStore.select(RecordEntity::class)
                .where(RecordEntity.ACCOUNT_ID.eq(accountId))
                .and(RecordEntity.DATE_OF.timespan(date, LocalDate.now()))
                .orderBy(RecordEntity.DATE_OF.asc())
                .get().observable().toList()
                .map { mapper.mapEntityToDomain(it) }
    }

    override fun get(id: Int): Single<Record> {
        return dataStore.select(RecordEntity::class)
                .where(RecordEntity.ID.eq(id))
                .get().observable().singleOrError()
                .map { mapper.mapEntityToDomain(it) }

    }

    override fun getAllForAccount(accountId: Int): Single<List<Record>> {
        return dataStore.select(RecordEntity::class)
                .where(RecordEntity.ACCOUNT_ID.eq(accountId))
                .get().observable().toList()
                .map { mapper.mapEntityToDomain(it) }
    }

    override fun getAll(): Single<List<Record>> {
        return dataStore.select(RecordEntity::class)
                .get().observable().toList()
                .map { mapper.mapEntityToDomain(it) }
    }

    override fun add(model: com.loh.skint.data.entity.Record): Single<Record> {
        return dataStore.insert(model)
                .map { mapper.mapEntityToDomain(it) }
    }

    override fun update(model: com.loh.skint.data.entity.Record): Single<Record> {
        return dataStore.update(model)
                .map { mapper.mapEntityToDomain(it) }
    }
}