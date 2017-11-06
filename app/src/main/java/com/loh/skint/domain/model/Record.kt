package com.loh.skint.domain.model

import com.loh.skint.data.entity.Account
import com.loh.skint.data.entity.TransferType
import java.math.BigDecimal
import java.util.*

data class Record(
        val uuid: UUID,
        var transferType: TransferType,
        var amount: BigDecimal,
        var date: Date,
        var account: Account)