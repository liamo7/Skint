package com.loh.skint.domain.mapper

import com.loh.skint.data.entity.Goal
import com.loh.skint.data.entity.GoalEntity
import javax.inject.Inject

class GoalMapper @Inject constructor(private val mapper: GoalRecordMapper) : Mapper<com.loh.skint.data.entity.Goal, com.loh.skint.domain.model.Goal> {

    override fun mapEntityToDomain(entity: Goal): com.loh.skint.domain.model.Goal {
        return com.loh.skint.domain.model.Goal(
                entity.uuid,
                entity.name,
                entity.note,
                entity.iconResId,
                entity.startDate,
                entity.targetDate,
                entity.savedAmount,
                entity.targetAmount,
                entity.account.uuid,
                mapper.mapEntityToDomain(entity.records)
        )
    }

    override fun mapDomainToEntity(domain: com.loh.skint.domain.model.Goal): Goal {
        return GoalEntity().apply {
            uuid = domain.uuid
            name = domain.name
            note = domain.note
            iconResId = domain.iconResId
            startDate = domain.startDate
            targetDate = domain.targetDate
            savedAmount = domain.savedAmount
            targetAmount = domain.targetAmount
            accountUUID = domain.accountUUID
            records.addAll(0, mapper.mapDomainToEntity(domain.records))
        }
    }
}