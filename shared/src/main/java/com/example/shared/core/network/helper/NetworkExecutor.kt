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

    private var onResult: ((Result<T>) -> Unit)? = null
    private var loadingBlock: (() -> Unit)? = null
    private var onSuccessBlock: ((T) -> Unit)? = null
    private var onErrorBlock: ((Exception) -> Unit)? = null

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

    fun loading(block: () -> Unit) {
        loadingBlock = block
    }

    fun success(block: (T) -> Unit) {
        onSuccessBlock = block
    }

    fun error(block: (Exception) -> Unit) {
        onErrorBlock = block
    }

    fun onResult(block: (Result<T>) -> Unit) {
        onResult = block
    }
}
