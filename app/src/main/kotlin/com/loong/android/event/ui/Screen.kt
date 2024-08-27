package com.loong.android.event.ui

import androidx.navigation.NamedNavArgument

sealed class Screen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object Calendar : Screen("calendar")

    data object Tag : Screen("tag")

    data object Notebook : Screen("notebook")

    data object Note : Screen("note")
}