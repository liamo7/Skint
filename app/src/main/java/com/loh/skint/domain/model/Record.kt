package com.loh.skint.domain.model

import com.loh.skint.data.entity.Account
import com.loh.skint.data.entity.TransferType
import org.threeten.bp.LocalDate
import java.math.BigDecimal
import java.util.*

data class Record(
        val uuid: UUID,
        val dbId: Int,
        var transferType: TransferType,
        var amount: BigDecimal,
        var date: LocalDate,
        var account: Account)