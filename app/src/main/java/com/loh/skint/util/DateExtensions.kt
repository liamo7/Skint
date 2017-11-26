package com.loh.skint.util

import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit
import org.threeten.bp.temporal.TemporalAdjusters.*
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

val LONG_DATE_FORMAT = DateTimeFormatter.ofPattern("E dd MMM YYYY")
val SHORT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/YYYY")

fun LocalDate.daysBetween(date: LocalDate) = ChronoUnit.DAYS.between(this, date).toInt()
fun LocalDate.weeksBetween(date: LocalDate) = ChronoUnit.WEEKS.between(this, date).toInt()
fun LocalDate.monthsBetween(date: LocalDate) = ChronoUnit.MONTHS.between(this, date).toInt()
fun LocalDate.yearsBetween(date: LocalDate) = ChronoUnit.YEARS.between(this, date).toInt()

sealed class DateRange(val id: Int, val timespan: Int) : Serializable {
    object DAY : DateRange(0, DAYS_BETWEEN)
    object WEEK : DateRange(1, WEEKS_BETWEEN)
    object MONTH : DateRange(2, MONTHS_BETWEEN)
    object YEAR : DateRange(3, YEARS_BETWEEN)

    companion object {
        fun values() = listOfNotNull(DAY, WEEK, MONTH, YEAR)
    }
}

fun LocalDate.calculateViewpagerPositionFromDateRange(dateRange: DateRange): Int {
    return when (dateRange) {
        is DateRange.DAY -> START_OF_TIME.daysBetween(this)
        is DateRange.WEEK -> START_OF_TIME.weeksBetween(this)
        is DateRange.MONTH -> START_OF_TIME.monthsBetween(this)
        is DateRange.YEAR -> START_OF_TIME.yearsBetween(this)
    }
}

fun calculateDateFromViewPager(position: Int, dateRange: DateRange): LocalDate {
    return when (dateRange) {
        is DateRange.DAY -> START_OF_TIME.plusDays(position.toLong())
        is DateRange.WEEK -> START_OF_TIME.plusWeeks(position.toLong())
        is DateRange.MONTH -> START_OF_TIME.plusMonths(position.toLong())
        is DateRange.YEAR -> START_OF_TIME.plusYears(position.toLong())
    }
}

fun calculateTimespan(dateRange: DateRange, date: LocalDate): Pair<LocalDate, LocalDate> {
    return when (dateRange) {
        is DateRange.YEAR -> date.with(firstDayOfYear()) to date.with(lastDayOfYear())
        is DateRange.MONTH -> date.with(firstDayOfMonth()) to date.with(lastDayOfMonth())
        is DateRange.WEEK -> date.with(DayOfWeek.MONDAY) to date.with(DayOfWeek.SUNDAY)
        is DateRange.DAY -> date to date
    }
}