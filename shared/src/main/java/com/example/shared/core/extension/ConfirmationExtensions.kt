package com.example.shared.core.extension

import com.example.shared.core.network.model.Result

fun <T> Result<T>.ifSuccess(block: (T)-> Unit): Result<T> {
    checkResult<Result.Success<T>>(this) {
        block(data)
    }

    return this
}

fun <T> Result<T>.ifError(block: (Exception) -> Unit): Result<T> {
    checkResult<Result.Error>(this) {
        block(exception)
    }

    return this
}

fun <T> Result<T>.ifLoading(block: () -> Unit): Result<T> {
    checkResult<Result.Loading>(this) {
        block()
    }

    return this
}

private inline fun <reified TARGET> checkResult(current: Result<*>, block: TARGET.() -> Unit) {
    if (current is TARGET) block(current)
}