package com.loh.skint.data.entity

import com.loh.skint.data.converter.BigDecimalConverter
import com.loh.skint.data.converter.CategoryConverter
import com.loh.skint.data.converter.LocalDateConverter
import com.loh.skint.domain.model.Category
import io.requery.*
import org.threeten.bp.LocalDate
import java.math.BigDecimal
import java.util.*

@Entity(name = "RecordEntity")
interface Record : Persistable {
    @get:[Key Generated] val id: Int
    @get:Column(unique = true, index = true) var uuid: UUID
    var transferType: TransferType
    @get:Convert(BigDecimalConverter::class) var amount: BigDecimal
    @get:Convert(LocalDateConverter::class) var dateOf: LocalDate
    @get:Convert(CategoryConverter::class) var category: Category
    @get:ManyToOne var account: Account
    @get:Column(value = "", nullable = false) var note: String
    var accountUUID: UUID
}