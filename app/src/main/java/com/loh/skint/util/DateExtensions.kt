package com.loh.skint.util

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit
import java.io.Serializable


val START_OF_TIME = LocalDate.of(1900, 1, 1)
val END_OF_TIME = LocalDate.of(2099, 12, 1)

val DAYS_BETWEEN = START_OF_TIME.daysBetween(END_OF_TIME)
val WEEKS_BETWEEN = START_OF_TIME.weeksBetween(END_OF_TIME)
val MONTHS_BETWEEN = START_OF_TIME.monthsBetween(END_OF_TIME)
val YEARS_BETWEEN = START_OF_TIME.yearsBetween(END_OF_TIME)

val DAY_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy")
val WEEK_FORMAT = DateTimeFormatter.ofPattern("dd LLL yyyy")
val MONTH_FORMAT = DateTimeFormatter.ofPattern("LLL yyyy")
val YEAR_FORMAT = DateTimeFormatter.ofPattern("yyyy")

fun LocalDate.daysBetween(date: LocalDate) = ChronoUnit.DAYS.between(this, date).toInt()
fun LocalDate.weeksBetween(date: LocalDate) = ChronoUnit.WEEKS.between(this, date).toInt()
fun LocalDate.monthsBetween(date: LocalDate) = ChronoUnit.MONTHS.between(this, date).toInt()
fun LocalDate.yearsBetween(date: LocalDate) = ChronoUnit.YEARS.between(this, date).toInt()

sealed class DateRange(val timespan: Int) : Serializable {
    object DAY : DateRange(DAYS_BETWEEN)
    object WEEK : DateRange(WEEKS_BETWEEN)
    object MONTH : DateRange(MONTHS_BETWEEN)
    object YEAR : DateRange(YEARS_BETWEEN)
}

fun LocalDate.calculateIndexFromRange(dateRange: DateRange): Int {
    return when (dateRange) {
        is DateRange.DAY -> daysBetween(START_OF_TIME)
        is DateRange.WEEK -> weeksBetween(START_OF_TIME)
        is DateRange.MONTH -> monthsBetween(START_OF_TIME)
        is DateRange.YEAR -> yearsBetween(START_OF_TIME)
    }
}

fun calculateDateFromViewPager(position: Int, dateRange: DateRange): LocalDate {
    return when (dateRange) {
        is DateRange.DAY -> START_OF_TIME.plusDays(position.toLong())
        is DateRange.WEEK -> START_OF_TIME.plusDays(position.toLong())
        is DateRange.MONTH -> START_OF_TIME.plusDays(position.toLong())
        is DateRange.YEAR -> START_OF_TIME.plusDays(position.toLong())
    }
}