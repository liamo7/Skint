package com.loh.skint.domain.usecase.goal

import com.loh.skint.data.entity.TransferType
import com.loh.skint.domain.mapper.GoalMapper
import com.loh.skint.domain.mapper.GoalRecordMapper
import com.loh.skint.domain.model.Goal
import com.loh.skint.domain.model.GoalRecord
import com.loh.skint.domain.repository.GoalRepository
import com.loh.skint.domain.usecase.SingleUseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.threeten.bp.LocalDate
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

class AddGoalRecord @Inject constructor(compositeDisposable: CompositeDisposable,
                                        private val goalMapper: GoalMapper,
                                        private val goalRecordMapper: GoalRecordMapper,
                                        private val goalRepository: GoalRepository)
    : SingleUseCase<Goal, AddGoalRecord.Params>(compositeDisposable) {

    override fun build(params: Params): Single<Goal> {
        val goalEntity = goalRepository.getBlocking(params.goalUUID)
        val record = GoalRecord(UUID.randomUUID(), LocalDate.now(), params.savedAmount, TransferType.INCOME, goalEntity.uuid)
        val recordEntity = goalRecordMapper.mapDomainToEntity(record)

        // add the record to the goal
        recordEntity.goal = goalEntity
        recordEntity.goalUUID = goalEntity.uuid
        goalEntity.records.add(recordEntity)

        // update the saved amount in goal
        goalEntity.savedAmount = goalEntity.savedAmount.add(params.savedAmount)

        // determine if the goal has been reached
        if (goalEntity.savedAmount >= goalEntity.targetAmount && !goalEntity.completed) {
            goalEntity.completed = true
            goalEntity.dateCompleted = LocalDate.now()
        }

        return goalRepository.update(goalEntity)
    }

    data class Params(val goalUUID: UUID, val savedAmount: BigDecimal)
}