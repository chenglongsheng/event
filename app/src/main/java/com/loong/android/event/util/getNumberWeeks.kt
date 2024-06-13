package com.loong.android.event.util

import java.time.YearMonth
import java.time.temporal.WeekFields

/**
 * 周开始时间，周一开始
 */
val WEEK_STARTS_ON: WeekFields = WeekFields.ISO

/**
 * 获取该月的周数量
 */
fun YearMonth.getWeekNumber(weekFields: WeekFields = WEEK_STARTS_ON): Int {
    val firstWeekNumber = this.atDay(1)[weekFields.weekOfMonth()]
    val lastWeekNumber = this.atEndOfMonth()[weekFields.weekOfMonth()]
    return lastWeekNumber - firstWeekNumber + 1 // Both weeks inclusive
}