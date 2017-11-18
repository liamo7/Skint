package com.loh.skint.data.entity

import com.loh.skint.data.converter.BigDecimalConverter
import com.loh.skint.data.converter.LocalDateConverter
import io.requery.*
import org.threeten.bp.LocalDate
import java.math.BigDecimal
import java.util.*

@Entity(name = "GoalRecordEntity")
interface GoalRecord : Persistable {
    @get:[Key Generated] val id: Int
    @get:Column(unique = true, index = true) var uuid: UUID
    @get:Convert(LocalDateConverter::class) var date: LocalDate
    @get:Convert(BigDecimalConverter::class) var amount: BigDecimal
    var transferType: TransferType
    @get:ManyToOne var goal: Goal
    var goalUUID: UUID
}