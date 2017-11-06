package com.loh.skint.domain.model

import android.content.Context
import java.math.BigDecimal
import java.util.*

data class Account(val uuid: UUID,
                   val name: String,
                   var balance: BigDecimal,
                   val currency: Currency,
                   val iconResName: String,
                   var records: MutableList<Record>?) {

    fun getIconResId(context: Context): Int {
        return context.resources.getIdentifier(iconResName, "drawable", context.packageName)
    }

//    fun addRecords(records: List<Record>) {
//        for (rec in records) {
//            addRecord(rec)
//        }
//    }
//
//    fun addRecord(record: Record) {
//        records?.let {
//            it.add(record)
//            updateBalance(record.transferType, record.amount)
//        }
//    }
//
//    private fun updateBalance(transferType: TransferType, amount: BigDecimal) {
//        balance = when (transferType) {
//            TransferType.INCOME -> balance.add(amount)
//            TransferType.EXPENSE -> balance.subtract(amount)
//        }
//    }
}