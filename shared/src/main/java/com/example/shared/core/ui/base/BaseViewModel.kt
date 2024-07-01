package com.example.shared.core.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.core.network.helper.NetworkExecutor
import com.example.shared.core.ui.util.state.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.job

abstract class BaseViewModel: ViewModel() {
    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Idle)
    val screenState = _screenState.asStateFlow()

    protected fun <T> networkExecutor(block: NetworkExecutor<T>.() -> Unit) =
        NetworkExecutor<T>(viewModelScope.coroutineContext.job).apply(block).makeNetworkCall()

    protected suspend fun updateScreenState(state: ScreenState) {
        _screenState.emit(state)
    }
}