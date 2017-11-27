package com.loh.skint.domain.model

import org.threeten.bp.LocalDate
import java.math.BigDecimal
import java.util.*

data class Account(val uuid: UUID,
                   var name: String,
                   var balance: BigDecimal,
                   var currency: Currency,
                   var dateCreated: LocalDate,
                   var accountIcon: Int,
                   var records: MutableList<Record> = mutableListOf(),
                   var goals: MutableList<Goal> = mutableListOf()) {

    fun prettyBalance() = "${currency.symbol}${balance.toPlainString()}"
}

data class AccountIcon(val id: Int, val iconResId: Int, val nameResId: Int)