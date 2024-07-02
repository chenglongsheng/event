package com.loong.android.event.ui.calendar.model

import java.time.LocalDate
import java.time.YearMonth

data class Month(
    val yearMonth: YearMonth,
    val weeks: List<List<LocalDate>>
)