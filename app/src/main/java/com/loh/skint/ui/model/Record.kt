package com.loh.skint.ui.model

import com.loh.skint.data.entity.TransferType
import org.threeten.bp.LocalDate
import java.math.BigDecimal
import java.util.*

data class Record(
        val uuid: UUID,
        val dbId: Int,
        val transferType: TransferType,
        val amount: BigDecimal,
        val date: LocalDate,
        val accountId: UUID) {
}