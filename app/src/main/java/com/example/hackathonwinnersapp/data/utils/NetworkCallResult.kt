package com.example.hackathonwinnersapp.data.utils

sealed class NetworkCallResult<out T> {
    data class Success<out T>(val value: T) : NetworkCallResult<T>()

    sealed class Error : NetworkCallResult<Nothing>() {
        data class General(
            val statusCode: String?,
            val message: String?
        ) : Error()

        data class Unknown(
            val code: Int? = null
        ) : Error()

        object Network : Error()
    }
}

