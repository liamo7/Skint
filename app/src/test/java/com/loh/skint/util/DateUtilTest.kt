package com.loh.skint.util

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.threeten.bp.LocalDate
import org.threeten.bp.Month


@RunWith(MockitoJUnitRunner::class)
class DateUtilTest {

    @Test
    fun test_calculate_pager_position_with_days_date_range() {
        val dateRange = DateRange.DAY

        // test first day of time
        val expectedFirstPosition = 0
        assertEquals(expectedFirstPosition, START_OF_TIME.calculateViewpagerPositionFromDateRange(dateRange))

        // test last day of time
        val expectedLastPosition = DAYS_BETWEEN
        assertEquals(expectedLastPosition, END_OF_TIME.calculateViewpagerPositionFromDateRange(dateRange))

        // test some point of time 73048
        val expectedPosition = 42960
        val date = LocalDate.of(2017, Month.AUGUST, 15)
        assertEquals(expectedPosition, date.calculateViewpagerPositionFromDateRange(dateRange))
    }

    @Test
    fun test_calculate_pager_position_with_weeks_date_range() {
        val dateRange = DateRange.WEEK

        // test first day of time
        val expectedFirstPosition = 0
        assertEquals(expectedFirstPosition, START_OF_TIME.calculateViewpagerPositionFromDateRange(dateRange))

        // test last day of time
        val expectedLastPosition = WEEKS_BETWEEN
        assertEquals(expectedLastPosition, END_OF_TIME.calculateViewpagerPositionFromDateRange(dateRange))

        val expected2ndWeekPosition = 1
        val date = LocalDate.of(1900, Month.JANUARY, 14)
        assertEquals(expected2ndWeekPosition, date.calculateViewpagerPositionFromDateRange(dateRange))
    }

    @Test
    fun test_calculate_pager_position_with_months_date_range() {
        val dateRange = DateRange.MONTH

        // test first day of time
        val expectedFirstPosition = 0
        assertEquals(expectedFirstPosition, START_OF_TIME.calculateViewpagerPositionFromDateRange(dateRange))

        // test last day of time
        val expectedLastPosition = MONTHS_BETWEEN
        assertEquals(expectedLastPosition, END_OF_TIME.calculateViewpagerPositionFromDateRange(dateRange))

        // test some point of time 73048
        val expectedPosition = 5
        val date = LocalDate.of(1900, Month.JUNE, 20)
        assertEquals(expectedPosition, date.calculateViewpagerPositionFromDateRange(dateRange))

        val expected1901Position = 12
        val date1901 = LocalDate.of(1901, Month.JANUARY, 20)
        assertEquals(expected1901Position, date1901.calculateViewpagerPositionFromDateRange(dateRange))
    }

    @Test
    fun test_calculate_pager_position_with_years_date_range() {
        val dateRange = DateRange.YEAR

        // test first day of time
        val expectedFirstPosition = 0
        assertEquals(expectedFirstPosition, START_OF_TIME.calculateViewpagerPositionFromDateRange(dateRange))

        // test last day of time
        val expectedLastPosition = YEARS_BETWEEN
        assertEquals(expectedLastPosition, END_OF_TIME.calculateViewpagerPositionFromDateRange(dateRange))

        // test some point of time 73048
        val expected2017 = 117
        val date = LocalDate.of(2017, Month.JANUARY, 10)
        assertEquals(expected2017, date.calculateViewpagerPositionFromDateRange(dateRange))
    }
}