package com.loh.skint.ui.model

import com.loh.skint.data.entity.TransferType
import java.math.BigDecimal
import java.util.*

data class Record(
        val uuid: UUID,
        val transferType: TransferType,
        val amount: BigDecimal,
        val date: Date,
        val accountId: UUID)