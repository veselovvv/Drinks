package com.veselovvv.drinks.core

import retrofit2.HttpException
import java.net.UnknownHostException

interface Mapper {
    interface Data<D, R> : Mapper {
        fun map(data: D): R
    }

    interface DataToDomain<S, R> : Data<S, R> {
        fun map(e: Exception): R

        abstract class Base<S, R> : DataToDomain<S, R> {
            protected fun errorType(e: Exception) = when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        }
    }

    enum class ErrorType {
        NO_CONNECTION,
        SERVICE_UNAVAILABLE,
        GENERIC_ERROR
    }
}