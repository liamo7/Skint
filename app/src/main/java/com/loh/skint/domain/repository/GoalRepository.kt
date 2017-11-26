package com.loh.skint.domain.repository

import com.loh.skint.domain.model.Goal
import com.loh.skint.domain.model.GoalRecord
import io.reactivex.Single
import org.threeten.bp.LocalDate
import java.util.*

interface GoalRepository : Repository<com.loh.skint.data.entity.Goal, com.loh.skint.domain.model.Goal> {
    fun getAll(accountUUID: UUID): Single<MutableList<Goal>>
    fun getRecords(goalUUID: UUID): Single<MutableList<GoalRecord>>
    fun getBlocking(uuid: UUID): com.loh.skint.data.entity.Goal
    fun getUpcoming(accountUUID: UUID, datePair: Pair<LocalDate, LocalDate>, reachedThreshold: Int): Single<MutableList<Goal>>
}