package com.example.shared.core.network.helper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class NetworkExecutor<T> internal constructor(parentJob: Job) {
    private val executorScope = CoroutineScope(Dispatchers.IO + SupervisorJob(parentJob))

    private lateinit var executeBlock: (suspend CoroutineScope.() -> T)

    private var loadingBlock: (suspend () -> Unit)? = null
    private var onSuccessBlock: (suspend (T) -> Unit)? = null
    private var onErrorBlock: (suspend (Exception) -> Unit)? = null

    internal fun makeNetworkCall() = executorScope.launch {
        loadingBlock?.invoke()

        try {
            val result = executeBlock.invoke(executorScope)

            onSuccessBlock?.invoke(result)
        } catch (e: Exception) {
            if (e is CancellationException) throw e

            onErrorBlock?.invoke(e)
        }
    }

    fun execute(block: suspend CoroutineScope.() -> T) {
        executeBlock = block
    }

    fun loading(block: suspend () -> Unit) {
        loadingBlock = block
    }

    fun success(block: suspend (T) -> Unit) {
        onSuccessBlock = block
    }

    fun error(block: suspend (Exception) -> Unit) {
        onErrorBlock = block
    }
}
