package com.loh.skint.data.converter

import io.requery.Converter
import org.threeten.bp.LocalDate

class LocalDateConverter : Converter<LocalDate, String> {

    override fun getMappedType(): Class<LocalDate> = LocalDate::class.java

    override fun getPersistedType(): Class<String> = String::class.java

    override fun getPersistedSize(): Int? = null

    override fun convertToMapped(type: Class<out LocalDate>?, value: String?): LocalDate? =
            value?.let { return LocalDate.parse(it) }

    override fun convertToPersisted(value: LocalDate?): String? = value?.toString()

}