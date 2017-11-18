package com.loh.skint.domain.model

import com.loh.skint.data.entity.TransferType
import org.threeten.bp.LocalDate
import java.math.BigDecimal
import java.util.*

data class GoalRecord(val uuid: UUID,
                      val date: LocalDate,
                      val amount: BigDecimal,
                      val transferType: TransferType,
                      val goalUUID: UUID)