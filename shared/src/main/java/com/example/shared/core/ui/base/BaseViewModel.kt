package com.example.shared.core.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.core.network.helper.NetworkExecutor
import kotlinx.coroutines.job

abstract class BaseViewModel: ViewModel() {
    protected fun <T> networkExecutor(block: NetworkExecutor<T>.() -> Unit) =
        NetworkExecutor<T>(viewModelScope.coroutineContext.job).apply(block).makeNetworkCall()
}