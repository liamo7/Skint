package com.loh.skint.domain.mapper

import android.content.Context
import com.loh.skint.data.entity.Record
import com.loh.skint.data.entity.RecordEntity
import com.loh.skint.injection.qualifier.App
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecordMapper @Inject constructor(@App val context: Context) : Mapper<com.loh.skint.data.entity.Record, com.loh.skint.domain.model.Record> {

    override fun mapEntityToDomain(entity: Record): com.loh.skint.domain.model.Record {
        return com.loh.skint.domain.model.Record(
                entity.uuid,
                entity.transferType,
                entity.amount,
                entity.dateOf,
                entity.category,
                entity.note,
                entity.account.uuid,
                entity.currency
        )
    }

    override fun mapDomainToEntity(domain: com.loh.skint.domain.model.Record): Record {
        return RecordEntity().apply {
            uuid = domain.uuid
            transferType = domain.transferType
            amount = domain.amount
            dateOf = domain.date
            category = domain.category
            note = domain.note
            currency = domain.currency
            accountUUID = domain.accountUUID
        }
    }
}