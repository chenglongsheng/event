package com.loong.android.event.ui.calendar.model

import androidx.compose.runtime.mutableStateOf
import com.loong.android.event.util.getWeekNumber
import java.time.LocalDate
import java.time.Period
import java.time.YearMonth
import java.time.temporal.ChronoUnit

/**
 * 日历状态
 */
class CalendarState {

    val calendarUiState = mutableStateOf(CalendarUiState())
    val listMonths: List<Month>

    /**
     * 开始到结束时期
     */
    private val periodBetweenCalendarStartEnd = Period.between(START_LOCAL_DATE, END_LOCAL_DATE)

    /**
     * 今天
     */
    val today: LocalDate
        get() = LocalDate.now()

    /**
     * 距 START_LOCAL_DATE 至今的月
     */
    val monthFromStart: Long
        get() = ChronoUnit.MONTHS.between(START_LOCAL_DATE, today)

    init {
        val tempListMonths = mutableListOf<Month>()
        var startYearMonth = YearMonth.from(START_LOCAL_DATE)
        for (numberMonth in 0..periodBetweenCalendarStartEnd.toTotalMonths()) {
            val numberWeeks = startYearMonth.getWeekNumber()
            val listWeekItems = mutableListOf<Week>()
            for (week in 0 until numberWeeks) {
                listWeekItems.add(Week(week, startYearMonth))
            }
            val month = Month(startYearMonth, listWeekItems)
            tempListMonths.add(month)
            startYearMonth = startYearMonth.plusMonths(1)
        }
        listMonths = tempListMonths.toList()
    }

    companion object {
        const val DAYS_IN_WEEK = 7

        /**
         * 日历开始日期
         */
        val START_LOCAL_DATE: LocalDate = LocalDate.of(1901, 1, 1)

        /**
         * 日历结束日期
         */
        val END_LOCAL_DATE: LocalDate = LocalDate.of(2099, 12, 31)
    }
}
