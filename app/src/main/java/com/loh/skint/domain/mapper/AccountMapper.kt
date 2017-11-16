package com.loh.skint.domain.mapper

import android.content.Context
import com.loh.skint.data.entity.AccountEntity
import com.loh.skint.domain.model.Account
import com.loh.skint.injection.qualifier.App
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountMapper @Inject constructor(@App val context: Context, val recordMapper: RecordMapper) : Mapper<com.loh.skint.data.entity.Account, com.loh.skint.domain.model.Account> {

    override fun mapEntityToDomain(entity: com.loh.skint.data.entity.Account): com.loh.skint.domain.model.Account {
        return Account(
                entity.uuid,
                entity.name,
                entity.balance,
                entity.currency,
                entity.dateCreated,
                Account.findIconById(entity.id),
                entity.records.let { recordMapper.mapEntityToDomain(it) }
        )
    }

    override fun mapDomainToEntity(domain: com.loh.skint.domain.model.Account): com.loh.skint.data.entity.Account {
        return AccountEntity().apply {
            uuid = domain.uuid
            name = domain.name
            currency = domain.currency
            balance = domain.balance
            dateCreated = domain.dateCreated
            iconId = domain.accountIcon.id
            records.addAll(0, recordMapper.mapDomainToEntity(domain.records))
        }
    }
}