package com.loong.android.event

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.loong.android.event.ui.calendar.Calendar
import com.loong.android.event.ui.calendar.model.CalendarState
import com.loong.android.event.ui.theme.EventTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventTheme {
                Calendar(CalendarState()) {
                }
            }
        }
    }
}