package com.loh.skint.domain.mapper

import android.content.Context
import com.loh.skint.data.entity.AccountEntity
import com.loh.skint.domain.model.Account
import com.loh.skint.injection.qualifier.App
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountMapper @Inject constructor(@App val context: Context) : Mapper<com.loh.skint.data.entity.Account, com.loh.skint.domain.model.Account, com.loh.skint.ui.model.Account> {

    override fun mapEntityToDomain(entity: com.loh.skint.data.entity.Account): com.loh.skint.domain.model.Account {
        return Account(entity.uuid, entity.name, BigDecimal(entity.balance), entity.iconResName)
    }

    override fun mapDomainToEntity(domain: com.loh.skint.domain.model.Account): com.loh.skint.data.entity.Account {
        return AccountEntity().apply {
            uuid = domain.uuid
            name = domain.name
            balance = domain.balance.toPlainString()
            iconResName = domain.iconResName
        }
    }

    override fun mapDomainToUi(domain: com.loh.skint.domain.model.Account): com.loh.skint.ui.model.Account {
        return com.loh.skint.ui.model.Account(
                domain.uuid, domain.name, domain.balance.toPlainString(), domain.getIconResId(context))
    }
}