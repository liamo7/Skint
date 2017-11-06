package com.loh.skint.data.repository

import com.loh.skint.data.entity.RecordEntity
import com.loh.skint.domain.mapper.RecordMapper
import com.loh.skint.domain.model.Record
import com.loh.skint.domain.repository.RecordRepository
import io.reactivex.Single
import io.requery.Persistable
import io.requery.kotlin.Logical
import io.requery.meta.AttributeDelegate
import io.requery.query.Expression
import io.requery.reactivex.KotlinReactiveEntityStore
import org.threeten.bp.LocalDate
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RecordRepository @Inject constructor(private val dataStore: KotlinReactiveEntityStore<Persistable>, private val mapper: RecordMapper) : RecordRepository {

    override fun getRecentRecords(accountId: Int, date: LocalDate): Single<List<Record>> {
        return dataStore.select(RecordEntity::class)
                .where(RecordEntity.ACCOUNT_ID.eq(accountId))
                .and(matchTimespan(date, LocalDate.now(), RecordEntity.DATE_OF))
                .orderBy(RecordEntity.DATE_OF.asc())
                .get().observable().toList()
                .map { mapper.mapEntityToDomain(it) }
    }

    override fun get(id: Int): Single<Record> {
        TODO("not implemented")
    }

    override fun getAll(): Single<List<Record>> {
        TODO("not implemented")
    }

    override fun add(model: com.loh.skint.data.entity.Record): Single<Record> {
        TODO("not implemented")
    }

    override fun update(model: com.loh.skint.data.entity.Record): Single<Record> {
        TODO("not implemented")
    }

    fun matchTimespan(start: LocalDate, end: LocalDate, queryExpression: AttributeDelegate<RecordEntity, LocalDate>):
            Logical<out Expression<LocalDate>, Any> {
        return queryExpression.between(start, end)
    }
}
