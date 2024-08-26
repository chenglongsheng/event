package com.loong.android.event.ui.calendar.model

import androidx.compose.runtime.mutableStateOf
import com.loong.android.event.util.getMonthWeeks
import java.time.LocalDate
import java.time.Period
import java.time.YearMonth
import java.time.temporal.ChronoUnit

/**
 * 日历状态
 */
class CalendarState {

    val calendarUiState = mutableStateOf(CalendarUiState())

    val monthList: List<Month>

    /**
     * 今天
     */
    val today: LocalDate
        get() = LocalDate.now()

    /**
     * 距 START_LOCAL_DATE 至今的月
     */
    val monthFromStart: Int
        get() = ChronoUnit.MONTHS.between(START_LOCAL_DATE, today).toInt()

    /**
     * 总共的月数
     */
    val totalMonthSize: Int

    init {
        val totalMonths = Period.between(START_LOCAL_DATE, END_LOCAL_DATE).toTotalMonths()
        totalMonthSize = totalMonths.toInt() + 1
        val tempMonthList = mutableListOf<Month>()
        var startDayOfMonth: YearMonth = YearMonth.from(START_LOCAL_DATE)
        for (i in 0 until totalMonthSize) {
            val month = Month(startDayOfMonth, startDayOfMonth.getMonthWeeks())
            tempMonthList.add(month)
            startDayOfMonth = startDayOfMonth.plusMonths(1)
        }
        monthList = tempMonthList
    }

    companion object {
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
