package com.loong.android.event.ui.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loong.android.event.ui.calendar.model.CalendarState
import com.loong.android.event.ui.calendar.model.CalendarUiState
import com.loong.android.event.ui.calendar.model.Month
import com.loong.android.event.ui.theme.EventTheme
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Calendar(
    calendarState: CalendarState = CalendarState(),
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    onDayClick: (date: LocalDate) -> Unit,
) {
    val calendarUiState = calendarState.calendarUiState.value

    val pagerState = rememberPagerState(
        initialPage = calendarState.monthFromStart.toInt(),
        pageCount = { calendarState.listMonths.size }
    )

    HorizontalPager(state = pagerState) {
        LazyColumn {
            val month = calendarState.listMonths[it]
            itemsCalendarMonth(calendarUiState, onDayClick, month)
        }
    }
}

private fun LazyListScope.itemsCalendarMonth(
    calendarUiState: CalendarUiState,
    onDayClicked: (LocalDate) -> Unit,
    month: Month
) {
    item(month.yearMonth.month.name + month.yearMonth.year + "header") {
        MonthHeader(
            modifier = Modifier.padding(start = 32.dp, end = 32.dp, top = 32.dp),
            month = month.yearMonth.month.name,
            year = month.yearMonth.year.toString()
        )
    }

    // Expanding width and centering horizontally
    val contentModifier = Modifier
        .fillMaxWidth()
        .wrapContentWidth(Alignment.CenterHorizontally)
    item(month.yearMonth.month.name + month.yearMonth.year + "daysOfWeek") {
        DaysOfWeek(modifier = contentModifier)
    }

    // A custom key needs to be given to these items so that they can be found in tests that
    // need scrolling. The format of the key is ${year/month/weekNumber}. Thus,
    // the key for the fourth week of December 2020 is "2020/12/4"
    itemsIndexed(month.weeks, key = { index, _ ->
        month.yearMonth.year.toString() + "/" + month.yearMonth.month.value + "/" + (index + 1).toString()
    }) { _, week ->
        Week(
            calendarUiState = calendarUiState,
            modifier = contentModifier,
            week = week,
            onDayClicked = {
                calendarUiState.selectedDate = it
                onDayClicked(it)
            }
        )
//        Spacer(Modifier.height(8.dp))
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