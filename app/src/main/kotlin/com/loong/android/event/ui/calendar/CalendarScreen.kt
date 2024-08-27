package com.loong.android.event.ui.calendar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CalendarScreen(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Calendar {
        onClick()
    }
}