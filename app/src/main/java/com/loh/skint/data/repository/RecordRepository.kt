package com.loh.skint.data.repository

import com.loh.skint.data.entity.RecordEntity
import com.loh.skint.domain.mapper.RecordMapper
import com.loh.skint.domain.model.Record
import com.loh.skint.domain.repository.RecordRepository
import com.loh.skint.util.DateRange
import com.loh.skint.util.calculateTimespan
import com.loh.skint.util.timespan
import io.reactivex.Single
import io.requery.Persistable
import io.requery.reactivex.KotlinReactiveEntityStore
import org.threeten.bp.LocalDate
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RecordRepository @Inject constructor(private val dataStore: KotlinReactiveEntityStore<Persistable>,
                                           private val mapper: RecordMapper) : RecordRepository {

    override fun get(uuid: UUID): Single<Record> {
        return dataStore.select(RecordEntity::class)
                .where(RecordEntity.UUID.eq(uuid))
                .get().observable().singleOrError()
                .map { mapper.mapEntityToDomain(it) }
    }

    override fun getAll(): Single<MutableList<Record>> {
        return dataStore.select(RecordEntity::class)
                .get().observable()
                .map { mapper.mapEntityToDomain(it) }
                .toList()
    }

    override fun add(model: com.loh.skint.data.entity.Record): Single<Record> {
        return dataStore.insert(model)
                .map { mapper.mapEntityToDomain(it) }
    }

    override fun update(model: com.loh.skint.data.entity.Record): Single<Record> {
        return dataStore.update(model)
                .map { mapper.mapEntityToDomain(it) }
    }

    override fun getRange(accountUUID: UUID, date: LocalDate, dateRange: DateRange): Single<MutableList<Record>> {
        return dataStore.select(RecordEntity::class)
                .where(RecordEntity.ACCOUNT_UUID.eq(accountUUID))
                .and(RecordEntity.DATE_OF.timespan(calculateTimespan(dateRange, date)))
                .orderBy(RecordEntity.DATE_OF.asc())
                .get().observable()
                .map { mapper.mapEntityToDomain(it) }
                .toList()
    }

    override fun getAllForAccount(accountUUID: UUID): Single<MutableList<Record>> {
        return dataStore.select(RecordEntity::class)
                .where(RecordEntity.ACCOUNT_UUID.eq(accountUUID))
                .get().observable()
                .map { mapper.mapEntityToDomain(it) }
                .toList()
    }

    override fun getRecentRecords(accountUUID: UUID, date: LocalDate): Single<MutableList<Record>> {
        return dataStore.select(RecordEntity::class)
                .where(RecordEntity.ACCOUNT_UUID.eq(accountUUID))
                .and(RecordEntity.DATE_OF.timespan(date, LocalDate.now()))
                .orderBy(RecordEntity.DATE_OF.asc())
                .get().observable()
                .map { mapper.mapEntityToDomain(it) }
                .toList()
    }
}