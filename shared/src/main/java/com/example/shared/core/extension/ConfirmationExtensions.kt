package com.example.shared.core.extension

import com.example.shared.core.network.model.Result

fun <T> Result<T>.ifSuccess(block: (T)-> Unit): Result<T> {
    if (this is Result.Success) block(data)

    return this
}

fun <T> Result<T>.ifError(block: (Exception) -> Unit): Result<T> {
    if (this is Result.Error) block(exception)

    return this
}

fun <T> Result<T>.ifLoading(block: () -> Unit): Result<T> {
    if (this is Result.Loading) block()

    return this
}
