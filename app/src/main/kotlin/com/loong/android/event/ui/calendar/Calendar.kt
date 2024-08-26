package com.loong.android.event.ui.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loong.android.event.ui.calendar.model.CalendarState
import com.loong.android.event.ui.theme.EventTheme
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Calendar(
    calendarState: CalendarState = CalendarState(),
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    onDayClick: (date: LocalDate) -> Unit,
) {
    var calendarUiState by remember { calendarState.calendarUiState }

    val pagerState = rememberPagerState(
        initialPage = calendarState.monthFromStart,
        pageCount = { calendarState.totalMonthSize }
    )

    HorizontalPager(state = pagerState, contentPadding = contentPadding) { pageIndex ->
        LazyColumn(userScrollEnabled = false) {
            val month = calendarState.monthList[pageIndex]
            val yearMonth = month.yearMonth
            item("month header $yearMonth") {
                MonthHeader(
                    month = yearMonth.monthValue.toString(),
                    year = yearMonth.year.toString(),
                    Modifier.padding(start = 32.dp, end = 32.dp, top = 32.dp)
                )
            }
            item("week header $yearMonth") {
                WeekHeader()
            }
            itemsIndexed(
                month.weeks,
                key = { index, _ -> "week ${index + 1} of $yearMonth" }) { _, weeks ->
                Row {
                    val space = Modifier
                        .weight(1f)
                        .width(48.dp)
                    Spacer(modifier = space)
                    weeks.forEach { date ->
                        Day(day = date) {
                            calendarUiState = calendarUiState.copy(selectDate = it)
                        }
                    }
                    Spacer(modifier = space)
                }
            }
        }
    }
}

/**
 * 月标头
 */
@Composable
fun MonthHeader(month: String, year: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier.clearAndSetSemantics { }) {
        Spacer(modifier = Modifier
            .width(48.dp)
            .weight(1f))
        Text(
            text = month,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = year,
            modifier = modifier.align(Alignment.CenterVertically),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
fun WeekHeader(modifier: Modifier = Modifier) {
    Row {
        val space = Modifier
            .weight(1f)
            .width(48.dp)
        Spacer(modifier = space)
        DayOfWeek.entries.forEach {
            val name = it.getDisplayName(TextStyle.NARROW, Locale.getDefault())
            DayOfWeekHeading(name)
        }
        Spacer(modifier = space)
    }
}

@Preview
@Composable
fun PreviewCalendar() {
    EventTheme {
        Calendar {

        }
    }
}