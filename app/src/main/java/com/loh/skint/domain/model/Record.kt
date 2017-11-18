package com.loh.skint.domain.model

import com.loh.skint.R
import com.loh.skint.data.entity.TransferType
import org.threeten.bp.LocalDate
import java.math.BigDecimal
import java.util.*

data class Record(
        val uuid: UUID,
        var transferType: TransferType,
        var amount: BigDecimal,
        var date: LocalDate,
        var category: Category,
        var note: String,
        var accountUUID: UUID,
        var currency: Currency? = null) {

    fun prettyAmount(): String {
        val signum = if (transferType == TransferType.EXPENSE) "-" else ""
        return "$signum${currency?.symbol}${amount.toPlainString()}"
    }

    fun colorizeAmount() = if (isPositve()) R.color.positive else R.color.negative

    fun isPositve() = transferType == TransferType.INCOME
}
