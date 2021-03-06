package com.loh.skint.data.repository

import com.loh.skint.data.entity.GoalEntity
import com.loh.skint.data.entity.GoalRecordEntity
import com.loh.skint.domain.mapper.GoalMapper
import com.loh.skint.domain.mapper.GoalRecordMapper
import com.loh.skint.domain.model.Goal
import com.loh.skint.domain.model.GoalRecord
import com.loh.skint.domain.repository.GoalRepository
import com.loh.skint.util.timespan
import io.reactivex.Completable
import io.reactivex.Single
import io.requery.Persistable
import io.requery.reactivex.KotlinReactiveEntityStore
import org.threeten.bp.LocalDate
import java.util.*
import javax.inject.Inject

class GoalRepository @Inject constructor(private val dataStore: KotlinReactiveEntityStore<Persistable>,
                                         private val mapper: GoalMapper,
                                         private val recordMapper: GoalRecordMapper)
    : GoalRepository {

    override fun get(uuid: UUID): Single<Goal> {
        return dataStore.select(com.loh.skint.data.entity.Goal::class)
                .where(GoalEntity.UUID.eq(uuid))
                .get().observable().singleOrError()
                .map { mapper.mapEntityToDomain(it) }
    }

    override fun getAll(): Single<MutableList<Goal>> {
        TODO("not implemented")
    }

    override fun getAll(accountUUID: UUID): Single<MutableList<Goal>> {
        return dataStore.select(com.loh.skint.data.entity.Goal::class)
                .where(GoalEntity.ACCOUNT_UUID.eq(accountUUID))
                .get().observable().toList()
                .map { mapper.mapEntityToDomain(it) }
    }

    override fun getRecords(goalUUID: UUID): Single<MutableList<GoalRecord>> {
        return dataStore.select(com.loh.skint.data.entity.GoalRecord::class)
                .where(GoalRecordEntity.GOAL_UUID.eq(goalUUID))
                .get().observable().toList()
                .map { recordMapper.mapEntityToDomain(it) }

    }

    override fun add(model: com.loh.skint.data.entity.Goal): Single<Goal> {
        return dataStore.insert(model)
                .map { mapper.mapEntityToDomain(it) }
    }

    override fun update(model: com.loh.skint.data.entity.Goal): Single<Goal> {
        return dataStore.update(model)
                .map { mapper.mapEntityToDomain(it) }
    }

    override fun getBlocking(uuid: UUID): com.loh.skint.data.entity.Goal {
        return dataStore.select(GoalEntity::class)
                .where(com.loh.skint.data.entity.GoalEntity.UUID.eq(uuid))
                .get().first()
    }

    override fun delete(uuid: UUID): Completable {
        return dataStore.delete(GoalEntity::class)
                .where(GoalEntity.UUID.eq(uuid))
                .get().single().toCompletable()
    }

    // upcoming goals are goals that are nearly reached (<=10%) or the target date is close
    override fun getUpcoming(accountUUID: UUID, datePair: Pair<LocalDate, LocalDate>, reachedThreshold: Int): Single<MutableList<Goal>> {
        return dataStore.select(com.loh.skint.data.entity.Goal::class)
                .where(GoalEntity.ACCOUNT_UUID.eq(accountUUID))
                .and(GoalEntity.TARGET_DATE.timespan(datePair))
                    .orderBy(GoalEntity.SAVED_AMOUNT, GoalEntity.TARGET_AMOUNT)
                .get().observable().toList()
                .map { mapper.mapEntityToDomain(it) }
    }
}