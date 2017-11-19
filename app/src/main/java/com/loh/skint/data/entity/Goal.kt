package com.loh.skint.data.entity

import com.loh.skint.data.converter.BigDecimalConverter
import com.loh.skint.data.converter.CurrencyConverter
import com.loh.skint.data.converter.LocalDateConverter
import com.loh.skint.domain.model.Currency
import io.requery.*
import org.threeten.bp.LocalDate
import java.math.BigDecimal
import java.util.*

@Entity(name = "GoalEntity")
interface Goal : Persistable {
    @get:[Key Generated] val id: Int
    @get:Column(unique = true, index = true) var uuid: UUID

    var name: String
    var note: String
    var iconResId: Int

    @get:Convert(LocalDateConverter::class) var startDate: LocalDate
    @get:Convert(LocalDateConverter::class) var targetDate: LocalDate

    @get:Convert(BigDecimalConverter::class) var savedAmount: BigDecimal
    @get:Convert(BigDecimalConverter::class) var targetAmount: BigDecimal

    @get:Convert(CurrencyConverter::class) var currency: Currency

    @get:OneToMany(mappedBy = "goal", cascade = arrayOf(CascadeAction.SAVE, CascadeAction.DELETE))
    val records: MutableList<GoalRecord>

    var accountUUID: UUID
    @get:ManyToOne var account: Account
}
