package com.loong.android.event.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import com.loong.android.event.R
import com.loong.android.event.ui.calendar.model.CalendarUiState
import java.time.LocalDate

@Composable
private fun DayContainer(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    backgroundColor: Color = Color.Transparent,
    onClickLabel: String? = null,
    content: @Composable () -> Unit
) {
    val stateDescriptionLabel = stringResource(
        if (selected) R.string.state_descr_selected else R.string.state_descr_not_selected
    )

    Box(modifier = modifier
        .size(width = CELL_SIZE, height = CELL_SIZE)
        .background(backgroundColor)
        .clickable(
            enabled = enabled, onClickLabel = onClickLabel, onClick = onClick
        )
        .semantics(mergeDescendants = true) {
            stateDescription = stateDescriptionLabel
        }) {
        content()
    }
}

/**
 *
 */
@Composable
internal fun DayOfWeekHeading(day: String) {
    DayContainer {
        Text(
            text = day,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

/**
 * @param day the day to display
 * @param calendarState the calendar state
 * @param onDayClicked callback when the day is clicked
 * @param modifier the modifier to apply to the day container
 */
@Composable
internal fun Day(
    day: LocalDate,
    calendarState: CalendarUiState,
    onDayClicked: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val selected = calendarState.selectedDate == day
    val backgroundColor = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        Color.Transparent
    }
    DayContainer(modifier = modifier
        .semantics {
            text = AnnotatedString("${day.year}-${day.month.value}-${day.dayOfMonth}")
        }
        .background(backgroundColor),
        selected = selected,
        onClick = { onDayClicked(day) },
        onClickLabel = stringResource(R.string.state_descr_selected)
    ) {
        Text(
            text = day.dayOfMonth.toString(),
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
                .clearAndSetSemantics { },
            style = MaterialTheme.typography.bodySmall
        )
    }
}