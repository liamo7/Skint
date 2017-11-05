package com.loh.skint.data.converter

import com.loh.skint.domain.model.Currency
import com.loh.skint.domain.model.findCurrencyByCode
import io.requery.Converter

class CurrencyConverter : Converter<Currency, String> {

    override fun getMappedType(): Class<Currency> = Currency::class.java

    override fun getPersistedType(): Class<String> = String::class.java

    override fun getPersistedSize(): Int? {
        return null
    }

    override fun convertToPersisted(value: Currency?): String? {
        return value?.code
    }

    override fun convertToMapped(type: Class<out Currency>?, value: String?): Currency? {
        return findCurrencyByCode(value)
    }
}