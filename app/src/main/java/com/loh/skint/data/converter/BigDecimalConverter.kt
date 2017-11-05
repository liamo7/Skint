package com.loh.skint.data.converter

import io.requery.Converter
import java.math.BigDecimal

class BigDecimalConverter : Converter<BigDecimal, String> {

    override fun getMappedType(): Class<BigDecimal> = BigDecimal::class.java

    override fun getPersistedType(): Class<String> = String::class.java

    override fun getPersistedSize(): Int? {
        return null
    }

    override fun convertToPersisted(value: BigDecimal?): String? {
        return value?.toPlainString()
    }

    override fun convertToMapped(type: Class<out BigDecimal>?, value: String?): BigDecimal? {
        return if (value == null) null else BigDecimal(value)
    }
}