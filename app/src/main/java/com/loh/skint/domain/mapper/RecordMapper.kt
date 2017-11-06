package com.loh.skint.domain.mapper

import android.content.Context
import com.loh.skint.data.entity.Record
import com.loh.skint.data.entity.RecordEntity
import com.loh.skint.injection.qualifier.App
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecordMapper @Inject constructor(@App val context: Context) : Mapper<com.loh.skint.data.entity.Record, com.loh.skint.domain.model.Record, com.loh.skint.ui.model.Record> {

    override fun mapEntityToDomain(entity: Record): com.loh.skint.domain.model.Record {
        return com.loh.skint.domain.model.Record(
                entity.uuid, entity.id, entity.transferType, entity.amount, entity.dateOf, entity.account
        )
    }

    override fun mapDomainToEntity(domain: com.loh.skint.domain.model.Record): Record {
        return RecordEntity().apply {
            uuid = domain.uuid
            transferType = domain.transferType
            amount = domain.amount
            dateOf = domain.date
            account = domain.account
        }
    }

    override fun mapDomainToUi(domain: com.loh.skint.domain.model.Record): com.loh.skint.ui.model.Record {
        return com.loh.skint.ui.model.Record(
                domain.uuid,
                domain.dbId,
                domain.transferType,
                domain.amount,
                domain.date,
                domain.account.uuid
        )
    }

}