package com.loong.android.event.ui.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.dp
import com.loong.android.event.ui.calendar.model.CalendarUiState
import com.loong.android.event.ui.calendar.model.Week
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

@Composable
internal fun DaysOfWeek(modifier: Modifier = Modifier) {
    Row(modifier = modifier.clearAndSetSemantics { }) {
        for (day in DayOfWeek.entries) {
            DayOfWeekHeading(day = day.name.take(3))
        }
    }
}

@Composable
internal fun Week(
    calendarUiState: CalendarUiState,
    week: Week,
    onDayClicked: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val beginningWeek = week.yearMonth.atDay(1).plusWeeks(week.number.toLong())
    var currentDay = beginningWeek.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

    Row(modifier = modifier) {
        Spacer(
            Modifier
                .weight(1f)
                .heightIn(max = CELL_SIZE)
        )
        for (i in 0..6) {
            if (currentDay.month == week.yearMonth.month) {
                Day(
                    calendarState = calendarUiState,
                    day = currentDay,
                    onDayClicked = onDayClicked
                )
            } else {
                Box(modifier = Modifier.size(CELL_SIZE))
            }
            currentDay = currentDay.plusDays(1)
        }
        Spacer(
            Modifier
                .weight(1f)
                .heightIn(max = CELL_SIZE)
        )
    }
}

internal val CELL_SIZE = 48.dp