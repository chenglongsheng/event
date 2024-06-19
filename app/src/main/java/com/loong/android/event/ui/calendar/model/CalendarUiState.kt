package com.loong.android.event.ui.calendar.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.time.LocalDate

/**
 * 日历UI状态
 *
 * @property selectedDate 选中的日期，默认当前日期
 */
class CalendarUiState(initialSelectedDate: LocalDate = LocalDate.now()) {
    var selectedDate by mutableStateOf(initialSelectedDate)
}
