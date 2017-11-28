package com.loh.skint.domain.model

import com.loh.skint.R
import org.threeten.bp.LocalDate
import java.math.BigDecimal
import java.util.*

data class Goal(val uuid: UUID,
                var name: String,
                var note: String,
                var iconResId: Int,
                var startDate: LocalDate,
                var targetDate: LocalDate?,
                var savedAmount: BigDecimal,
                var targetAmount: BigDecimal,
                var accountUUID: UUID,
                var completed: Boolean = false,
                var dateReached: LocalDate? = null,
                var currency: Currency? = null,
                var records: MutableList<GoalRecord> = mutableListOf()) {

    fun prettySavedAmount() = prettyAmount(savedAmount)
    fun prettyTargetAmount() = prettyAmount(targetAmount)
    private fun prettyAmount(amount: BigDecimal) = "${currency?.symbol}${amount.toPlainString()}"

    fun progress(): Int = ((100 / targetAmount.toFloat()) * savedAmount.toFloat()).toInt()

    fun status(): Int {
        return if (progress() == 100) R.string.status_goal_reached
        else if (progress() >= 100) R.string.status_goal_exceeded
        else if (progress() < 100 && LocalDate.now().isBefore(targetDate)) R.string.status_goal_in_progress
        else R.string.status_goal_failed
    }
}