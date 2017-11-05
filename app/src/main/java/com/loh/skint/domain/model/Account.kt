package com.loh.skint.domain.model

import android.content.Context
import java.math.BigDecimal
import java.util.*

data class Account(val uuid: UUID, val name: String, val balance: BigDecimal, val iconResName: String) {

    fun getIconResId(context: Context): Int {
        return context.resources.getIdentifier(iconResName, "drawable", context.packageName)
    }
}