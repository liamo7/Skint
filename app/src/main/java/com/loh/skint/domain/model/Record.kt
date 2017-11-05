package com.loh.skint.domain.model

import com.loh.skint.data.entity.Account
import com.loh.skint.data.entity.TransferType
import java.math.BigDecimal
import java.util.*

data class Record(
        val uuid: UUID,
        val transferType: TransferType,
        val amount: BigDecimal,
        val date: Date,
        val account: Account)