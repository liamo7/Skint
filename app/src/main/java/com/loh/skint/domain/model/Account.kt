package com.loh.skint.domain.model

import android.content.Context
import org.threeten.bp.LocalDate
import java.math.BigDecimal
import java.util.*

data class Account(val uuid: UUID,
                   var name: String,
                   var balance: BigDecimal,
                   var currency: Currency,
                   var dateCreated: LocalDate,
                   var iconResName: String,
                   var records: MutableList<Record> = mutableListOf()) {

    fun prettyBalance() = "${currency.symbol} ${balance.toPlainString()}"

    fun getIconResId(context: Context): Int {
        return context.resources.getIdentifier(iconResName, "drawable", context.packageName)
    }
}