package com.lsimanenka.domain

sealed interface AppResult<out D, out E> {
    data class Success<out D>(val data: D) : AppResult<D, Nothing>
    data class Error<out E>(val error: E) : AppResult<Nothing, E>
}