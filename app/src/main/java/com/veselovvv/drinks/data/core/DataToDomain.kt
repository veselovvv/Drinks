package com.veselovvv.drinks.data.core

import retrofit2.HttpException
import java.net.UnknownHostException

abstract class DataToDomain<S, R> {
    abstract fun map(source: S): R
    abstract fun map(exception: Exception): R

    protected fun getErrorType(exception: Exception) = when (exception) {
        is UnknownHostException -> ErrorType.NO_CONNECTION
        is HttpException -> ErrorType.SERVICE_UNAVAILABLE
        else -> ErrorType.GENERIC_ERROR
    }

    enum class ErrorType {
        NO_CONNECTION,
        SERVICE_UNAVAILABLE,
        GENERIC_ERROR
    }
}