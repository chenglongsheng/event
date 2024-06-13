package com.loong.android.event.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import com.loong.android.event.R
import com.loong.android.event.ui.calendar.model.CalendarUiState
import java.time.LocalDate
import java.time.YearMonth

@Composable
internal fun DayOfWeekHeading(day: String) {
    DayContainer {
        Text(
            text = day,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall.copy(Color.White.copy(alpha = 0.6f))
        )
    }
}

@Composable
private fun DayContainer(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: () -> Unit = {},
    onClickEnable: Boolean = true,
    backgroundColor: Color = Color.Transparent,
    onClickLabel: String? = null,
    content: @Composable () -> Unit
) {
    val stateDescriptionLabel =
        stringResource(if (selected) R.string.state_descr_selected else R.string.state_descr_not_selected)

    Box(modifier = modifier
        .size(width = CELL_SIZE, height = CELL_SIZE)
        .pointerInput(Unit) { // 处理触摸事件
            detectTapGestures {
                onClick()
            }
        }
        .then(if (onClickEnable) {
            modifier.semantics {  // 点击的说明语义
                stateDescription = stateDescriptionLabel
                onClick(onClickLabel, null)
            }
        } else {
            modifier.clearAndSetSemantics { }
        })
        .background(backgroundColor)) {
        content()
    }
}

@Composable
internal fun Day(
    day: LocalDate,
    calendarState: CalendarUiState,
    onDayClicked: (LocalDate) -> Unit,
    month: YearMonth,
    modifier: Modifier = Modifier
) {
    val selected = calendarState.isDateInSelectedPeriod(day)
    DayContainer(
        modifier = modifier.semantics {
            text = AnnotatedString(
                "${month.month.name.lowercase().capitalize(Locale.current)} " +
                        "${day.dayOfMonth} ${month.year}"
            )
            dayStatusProperty = selected
        },
        selected = selected,
        onClick = { onDayClicked(day) },
        onClickLabel = stringResource(id = R.string.state_descr_selected)
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

val DayStatusKey = SemanticsPropertyKey<Boolean>("DayStatusKey")
var SemanticsPropertyReceiver.dayStatusProperty by DayStatusKey