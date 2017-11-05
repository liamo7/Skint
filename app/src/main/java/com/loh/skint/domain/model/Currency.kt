package com.loh.skint.domain.model

data class Currency(val code: String, val name: String, val symbol: String, var iconResId: Int?)

val AVAILABLE_CURRENCIES = arrayListOf(
        Currency("EUR", "Euro", "€", null),
        Currency("USD", "United States Dollar", "$", null),
        Currency("GBP", "British Pound", "£", null))

fun findCurrencyByCode(abr: String?): Currency? {
    return AVAILABLE_CURRENCIES.firstOrNull { it.code == abr }
}