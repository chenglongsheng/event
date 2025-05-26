package com.loong.android.event

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.loong.android.event.ui.Route
import com.loong.android.event.ui.calendar.CalendarScreen
import com.loong.android.event.ui.note.NoteScreen
import com.loong.android.event.ui.notebook.NotebookScreen
import com.loong.android.event.ui.tag.TagScreen
import com.loong.android.event.ui.theme.EventTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventTheme {
                AppNavHost()
            }
        }
    }
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController, Route.Calendar) {
        composable<Route.Calendar> {
            CalendarScreen(navController)
        }
        composable<Route.Note> {
            NoteScreen(navController)
        }
        composable<Route.Notebook> {
            NotebookScreen()
        }
        composable<Route.Tag> {
            TagScreen { }
        }
    }
}

@Preview
@Composable
private fun EventAppPreview() {
    AppNavHost()
}