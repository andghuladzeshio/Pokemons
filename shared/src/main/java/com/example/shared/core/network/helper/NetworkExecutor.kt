package com.example.shared.core.network.helper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException
import com.example.shared.core.network.model.Result

class NetworkExecutor<T> internal constructor(parentJob: Job) {
    private val executorScope = CoroutineScope(Dispatchers.Main + SupervisorJob(parentJob))

    private lateinit var executeBlock: (suspend CoroutineScope.() -> T)

    private var onResult: (suspend (Result<T>) -> Unit)? = null
    private var loadingBlock: (suspend () -> Unit)? = null
    private var onSuccessBlock: (suspend (T) -> Unit)? = null
    private var onErrorBlock: (suspend (Exception) -> Unit)? = null

    internal fun makeNetworkCall() = executorScope.launch {
        loadingBlock?.invoke()
        onResult?.invoke(Result.Loading)

        try {
            var result: T
            withContext(Dispatchers.IO) {
                result = executeBlock.invoke(executorScope)
            }

            onSuccessBlock?.invoke(result)
            onResult?.invoke(Result.Success(result))
        } catch (e: Exception) {
            if (e is CancellationException) throw e

            onErrorBlock?.invoke(e)
            onResult?.invoke(Result.Error(e))
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

    fun onResult(block: suspend (Result<T>) -> Unit) {
        onResult = block
    }
}
