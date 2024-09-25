package com.loong.android.event.ui.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CalendarScreen(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column {
        Row {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = "")
            }
        }
        Calendar {

        }
    }
}

@Preview
@Composable
private fun CalendarScreenPreview() {
    CalendarScreen {

    }
}