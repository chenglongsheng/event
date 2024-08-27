package com.loong.android.event.ui

import androidx.compose.runtime.Composable
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

    NavHost(navController = navController, Screen.Calendar.route) {
        composable(Screen.Calendar.route) {
            CalendarScreen() {
                navController.navigate(Screen.Notebook.route)
            }
        }
        composable(Screen.Notebook.route) {
            NotebookScreen()
        }
        composable(Screen.Note.route) {
            NoteScreen()
        }
        composable(Screen.Tag.route) {
            TagScreen()
        }
    }
}