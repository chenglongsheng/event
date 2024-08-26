package com.loong.android.event.ui.calendar.model

import java.time.LocalDate

/**
 * 日历UI状态
 */
data class CalendarUiState(val selectDate: LocalDate = LocalDate.now())