package com.loh.skint.domain.mapper

import com.loh.skint.data.entity.AccountEntity
import com.loh.skint.domain.model.Account
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountMapper @Inject constructor(private val recordMapper: RecordMapper,
                                        private val goalMapper: GoalMapper)
    : Mapper<com.loh.skint.data.entity.Account, com.loh.skint.domain.model.Account> {

    override fun mapEntityToDomain(entity: com.loh.skint.data.entity.Account): com.loh.skint.domain.model.Account {
        return Account(
                entity.uuid,
                entity.name,
                entity.balance,
                entity.currency,
                entity.dateCreated,
                Account.findIconById(entity.id),
                entity.records.let { recordMapper.mapEntityToDomain(it) },
                entity.goals.let { goalMapper.mapEntityToDomain(it) }
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
            goals.addAll(0, goalMapper.mapDomainToEntity(domain.goals))
        }
    }
}