package com.loh.skint.util

import io.requery.kotlin.Logical
import io.requery.meta.AttributeDelegate
import io.requery.query.Expression
import org.threeten.bp.LocalDate

fun <V> AttributeDelegate<V, LocalDate>.timespan(startDate: LocalDate, endDate: LocalDate):
        Logical<out Expression<LocalDate>, Any> {
    return between(startDate, endDate)
}

fun <V> AttributeDelegate<V, LocalDate>.timespan(pair: Pair<LocalDate, LocalDate>):
        Logical<out Expression<LocalDate>, Any> {
    return timespan(pair.first, pair.second)
}