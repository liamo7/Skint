package com.loh.skint.ui.model

import android.support.annotation.DrawableRes
import java.util.*

data class Account(val uuid: UUID, val name: String, val balance: String, @DrawableRes val iconResId: Int) {
    val prettyBalance = "£ $balance"
}