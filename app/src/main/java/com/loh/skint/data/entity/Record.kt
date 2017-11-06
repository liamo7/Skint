package com.loh.skint.data.entity

import com.loh.skint.data.converter.BigDecimalConverter
import com.loh.skint.data.converter.LocalDateConverter
import io.requery.*
import org.threeten.bp.LocalDate
import java.math.BigDecimal
import java.util.*

@Entity(name = "RecordEntity")
interface Record : Persistable {
    @get:[Key Generated] val id: Int
    @get:Column(unique = true) var uuid: UUID
    var transferType: TransferType
    @get:Convert(BigDecimalConverter::class) var amount: BigDecimal
    @get:Convert(LocalDateConverter::class) var dateOf: LocalDate
    @get:ManyToOne
    var account: Account
}