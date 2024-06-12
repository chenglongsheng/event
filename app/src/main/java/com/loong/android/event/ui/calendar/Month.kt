package com.loong.android.event.ui.calendar

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview

/**
 * 月标头
 */
@Composable
fun MonthHeader(month: String, year: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier.clearAndSetSemantics { }) {
        Text(
            text = month,
            modifier.weight(1f),
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = year,
            modifier = modifier.align(Alignment.CenterVertically),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview
@Composable
fun PreviewMonthHeader() {
    MonthHeader(month = "06", year = "2024")
}