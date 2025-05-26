package com.loong.android.event.ui.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.loong.android.event.ui.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(navController: NavController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                { navController.navigate(Route.Note) },
                Modifier,
                RoundedCornerShape(50)
            ) {
                Icon(Icons.Default.Add, "Add Note")
            }
        }
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(it)
        ) {
            Row(Modifier.align(Alignment.End)) {
                IconButton({ navController.navigate(Route.Notebook) }) {
                    Icon(Icons.Default.Book, "Note book")
                }
                IconButton({ navController.navigate(Route.Tag) }) {
                    Icon(Icons.Default.Sell, "Note tag")
                }
                IconButton({ navController.navigate(Route.Note) }) {
                    Icon(Icons.Default.CalendarMonth, contentDescription = "")
                }
            }
            val datePickerState =
                rememberDatePickerState(initialSelectedDateMillis = 1578096000000)
            DatePicker(state = datePickerState)
            Text(
                "Selected date timestamp: ${datePickerState.selectedDateMillis ?: "no selection"}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}