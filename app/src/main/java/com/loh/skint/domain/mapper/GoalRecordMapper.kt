package com.loh.skint.domain.mapper

import com.loh.skint.data.entity.GoalRecord
import com.loh.skint.data.entity.GoalRecordEntity
import javax.inject.Inject

class GoalRecordMapper @Inject constructor() : Mapper<com.loh.skint.data.entity.GoalRecord, com.loh.skint.domain.model.GoalRecord> {

    override fun mapEntityToDomain(entity: GoalRecord): com.loh.skint.domain.model.GoalRecord {
        return com.loh.skint.domain.model.GoalRecord(
                entity.uuid,
                entity.date,
                entity.amount,
                entity.transferType,
                entity.goalUUID
        )
    }

    override fun mapDomainToEntity(domain: com.loh.skint.domain.model.GoalRecord): GoalRecord {
        return GoalRecordEntity().apply {
            uuid = domain.uuid
            date = domain.date
            amount = domain.amount
            transferType = domain.transferType
            goalUUID = domain.goalUUID
        }
    }
}