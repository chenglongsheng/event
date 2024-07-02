package com.loong.android.event.util

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters

/**
 * 获取该月的周数
 */
fun YearMonth.getMonthWeeks(): List<List<LocalDate>> {
    val firstDayOfMonth = this.atDay(1)
    val lastDayOfMonth = this.atEndOfMonth()

    val firstMonday = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
    val lastSunday = lastDayOfMonth.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))

    return generateSequence(firstMonday) { it.plusDays(1) }
        .takeWhile { it <= lastSunday }
        .chunked(7)
        .toList()
}

/**
 * 获取该天所属月的周数
 */
fun LocalDate.getMonthWeeks(): List<List<LocalDate>> = YearMonth.from(this).getMonthWeeks()