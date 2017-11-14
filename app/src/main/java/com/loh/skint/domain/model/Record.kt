package com.loh.skint.domain.model

import com.loh.skint.data.entity.TransferType
import org.threeten.bp.LocalDate
import java.math.BigDecimal
import java.util.*

data class Record(
        val uuid: UUID,
        var transferType: TransferType,
        var amount: BigDecimal,
        var date: LocalDate,
        var category: Category,
        var accountUUID: UUID)