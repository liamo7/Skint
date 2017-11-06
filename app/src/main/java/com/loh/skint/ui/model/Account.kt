package com.loh.skint.ui.model

import com.loh.skint.domain.model.Currency
import java.util.*

data class Account(val uuid: UUID,
                   val name: String,
                   val balance: String,
                   val currency: Currency,
                   val iconResId: Int,
                   val records: List<Record>?) {

    val prettyBalance = "${currency.symbol} $balance"
}