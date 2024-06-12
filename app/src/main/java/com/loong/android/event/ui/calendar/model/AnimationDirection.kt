package com.loong.android.event.ui.calendar.model

enum class AnimationDirection {
    FORWARDS,
    BACKWARDS;

    fun isBackwards() = this == BACKWARDS
    fun isForwards() = this == FORWARDS
}
