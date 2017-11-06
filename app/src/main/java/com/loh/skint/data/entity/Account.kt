package com.loh.skint.data.entity

import com.loh.skint.data.converter.BigDecimalConverter
import com.loh.skint.data.converter.CurrencyConverter
import com.loh.skint.data.converter.LocalDateConverter
import com.loh.skint.domain.model.Currency
import io.requery.*
import org.threeten.bp.LocalDate
import java.math.BigDecimal
import java.util.*

@Entity(name = "AccountEntity") interface Account : Persistable {
    @get:[Key Generated] val id: Int
    @get:Column(unique = true) var uuid: UUID
    var name: String
    @get:Convert(BigDecimalConverter::class) var balance: BigDecimal
    @get:Convert(CurrencyConverter::class) var currency: Currency
    @get:Convert(LocalDateConverter::class) var dateCreated: LocalDate
    var iconResName: String
    @get:OneToMany(mappedBy = "account")
    var records: List<Record>?
}