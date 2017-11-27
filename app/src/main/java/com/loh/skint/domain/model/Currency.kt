package com.loh.skint.domain.model

data class Currency(val code: String, val name: String, val symbol: String, var iconResId: Int?) {
    override fun toString(): String {
        return name
    }
}

val AVAILABLE_CURRENCIES = arrayListOf(
        Currency("GBP", "British Pound", "£", null),
        Currency("EUR", "Euro", "€", null),
        Currency("USD", "United States Dollar", "$", null))

fun findCurrencyByCode(abr: String?): Currency? {
    return AVAILABLE_CURRENCIES.firstOrNull { it.code == abr }
}