package com.carfax.application.base.presentation


sealed class ViewActionState<T> {
    data class Complete<T>(val result: T) : ViewActionState<T>()
    data class Error<T>(val error: ViewError) : ViewActionState<T>()
    class Loading<T> : ViewActionState<T>()

    companion object {
        fun <T> complete(result: T) = Complete(result)

        fun <T> failure(error: ViewError) = Error<T>(error)

        fun <T> loading() = Loading<T>()
    }
}