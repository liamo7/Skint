package com.loh.skint.domain.model

import org.threeten.bp.LocalDate
import java.math.BigDecimal
import java.util.*

data class Goal(val uuid: UUID,
                var name: String,
                var note: String,
                var iconResId: Int,
                var startDate: LocalDate,
                var targetDate: LocalDate,
                var savedAmount: BigDecimal,
                var targetAmount: BigDecimal,
                var currency: Currency,
                var accountUUID: UUID,
                var records: MutableList<GoalRecord> = mutableListOf()) {

    fun prettySavedAmount() = prettyAmount(savedAmount)
    fun prettyTargetAmount() = prettyAmount(targetAmount)
    private fun prettyAmount(amount: BigDecimal) = "${currency.symbol}${amount.toPlainString()}"

    fun progress(): Int = ((100 / targetAmount.toFloat()) * savedAmount.toFloat()).toInt()
}