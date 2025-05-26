package com.loong.android.event.ui

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Calendar : Route

    @Serializable
    data object Tag : Route

    @Serializable
    data object Notebook : Route

    @Serializable
    data object Note : Route
}