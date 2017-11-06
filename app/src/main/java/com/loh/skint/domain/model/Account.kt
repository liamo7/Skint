package com.loh.skint.domain.model

import android.content.Context
import com.loh.skint.data.entity.TransferType
import org.threeten.bp.LocalDate
import java.math.BigDecimal
import java.util.*

data class Account(val uuid: UUID,
                   val dbId: Int,
                   var name: String,
                   var balance: BigDecimal,
                   var currency: Currency,
                   var dateCreated: LocalDate,
                   var iconResName: String,
                   var records: MutableList<Record>?) {

    fun getIconResId(context: Context): Int {
        return context.resources.getIdentifier(iconResName, "drawable", context.packageName)
    }

    fun addRecords(records: List<Record>) {
        for (rec in records) {
            addRecord(rec)
        }
    }

    fun addRecord(record: Record) {
        records?.let {
            it.add(record)
            updateBalance(record.transferType, record.amount)
        }
    }

    private fun updateBalance(transferType: TransferType, amount: BigDecimal) {
        balance = when (transferType) {
            TransferType.INCOME -> balance.add(amount)
            TransferType.EXPENSE -> balance.subtract(amount)
        }
    }
}