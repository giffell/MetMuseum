package com.test.common

sealed class Result<out R> {

    class Success<out T>(val data: T) : Result<T>()
    class Error(val exception: Throwable) : Result<Nothing>()
    class Loading(val showProgress: Boolean) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success [data = $data]"
            is Error -> "Error [exception = $exception]"
            is Loading -> "Loading $showProgress"
        }
    }
}
