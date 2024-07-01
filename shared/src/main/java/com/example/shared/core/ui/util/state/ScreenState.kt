package com.example.shared.core.ui.util.state

import java.lang.Exception

sealed class ScreenState {
    data object Idle: ScreenState()
    data object Loading: ScreenState()
    data class Error(val exception: Exception): ScreenState()
}