package com.loh.skint.domain.model

import com.loh.skint.R
import org.threeten.bp.LocalDate
import java.math.BigDecimal
import java.util.*

data class Account(val uuid: UUID,
                   var name: String,
                   var balance: BigDecimal,
                   var currency: Currency,
                   var dateCreated: LocalDate,
                   var accountIcon: AccountIcon,
                   var records: MutableList<Record> = mutableListOf(),
                   var goals: MutableList<Goal> = mutableListOf()) {

    companion object {
        val ICONS = listOf(
                AccountIcon(0, R.drawable.ic_wallet, R.string.icon_wallet),
                AccountIcon(1, R.drawable.ic_credit_card, R.string.icon_credit_card),
                AccountIcon(2, R.drawable.ic_piggy_bank, R.string.icon_piggy),
                AccountIcon(3, R.drawable.ic_money_bag, R.string.icon_money_bag),
                AccountIcon(4, R.drawable.ic_coins, R.string.icon_coins),
                AccountIcon(5, R.drawable.ic_safebox, R.string.icon_safebox)
        )

        fun findIconById(id: Int) = ICONS.find { it.id == id } ?: Account.ICONS[0]
    }

    fun prettyBalance() = "${currency.symbol}${balance.toPlainString()}"
}

data class AccountIcon(val id: Int, val iconResId: Int, val nameResId: Int)