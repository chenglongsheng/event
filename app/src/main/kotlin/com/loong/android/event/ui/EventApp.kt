package com.loong.android.event.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.loong.android.event.ui.calendar.CalendarScreen
import com.loong.android.event.ui.note.NoteScreen
import com.loong.android.event.ui.notebook.NotebookScreen
import com.loong.android.event.ui.tag.TagScreen

@Composable
fun EventApp() {
    val navController = rememberNavController()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.Note.route)
                },
                shape = RoundedCornerShape(50)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "NoteAdd"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            NavHost(navController = navController, Screen.Calendar.route) {
                composable(Screen.Calendar.route) {
                    CalendarScreen {
                        navController.navigate(Screen.Notebook.route)
                    }
                }
                composable(Screen.Notebook.route) {
                    NotebookScreen()
                }
                composable(Screen.Note.route) {
                    NoteScreen {
                        navController.navigateUp()
                    }
                }
                composable(Screen.Tag.route) {
                    TagScreen(onBackClick = { navController.navigateUp() })
                }
            }
        }
    }
}

@Preview
@Composable
private fun EventAppPreview() {
    EventApp()
}