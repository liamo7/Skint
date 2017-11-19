package com.loh.skint.data.repository

import com.loh.skint.data.entity.GoalEntity
import com.loh.skint.data.entity.GoalRecordEntity
import com.loh.skint.domain.mapper.GoalMapper
import com.loh.skint.domain.mapper.GoalRecordMapper
import com.loh.skint.domain.model.Goal
import com.loh.skint.domain.model.GoalRecord
import com.loh.skint.domain.repository.GoalRepository
import io.reactivex.Single
import io.requery.Persistable
import io.requery.reactivex.KotlinReactiveEntityStore
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
        return dataStore.upsert(model)
                .map { mapper.mapEntityToDomain(it) }
    }
}