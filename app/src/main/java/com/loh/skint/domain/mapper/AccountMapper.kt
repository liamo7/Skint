package com.loh.skint.domain.mapper

import android.content.Context
import com.loh.skint.data.entity.AccountEntity
import com.loh.skint.domain.model.Account
import com.loh.skint.injection.qualifier.App
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountMapper @Inject constructor(@App val context: Context, val recordMapper: RecordMapper) : Mapper<com.loh.skint.data.entity.Account, com.loh.skint.domain.model.Account, com.loh.skint.ui.model.Account> {

    override fun mapEntityToDomain(entity: com.loh.skint.data.entity.Account): com.loh.skint.domain.model.Account {
        return Account(
                entity.uuid,
                entity.name,
                entity.balance,
                entity.currency,
                entity.iconResName,
                entity.records?.let { recordMapper.mapEntityToDomain(it).toMutableList() }
        )
    }

    override fun mapDomainToEntity(domain: com.loh.skint.domain.model.Account): com.loh.skint.data.entity.Account {
        val recs = domain.records?.let { recordMapper.mapDomainToEntity(it).toMutableList() }
        return AccountEntity().apply {
            uuid = domain.uuid
            name = domain.name
            currency = domain.currency
            balance = domain.balance
            iconResName = domain.iconResName
            records = recs
        }
    }

    override fun mapDomainToUi(domain: com.loh.skint.domain.model.Account): com.loh.skint.ui.model.Account {
        return com.loh.skint.ui.model.Account(
                domain.uuid, domain.name, domain.balance.toPlainString(), domain.currency, domain.getIconResId(context))
    }
}