package com.example.hackathonwinnersapp.domain.models

import com.example.hackathonwinnersapp.util.StringResource


sealed class RequestResult<out T> {
    class Success<T>(val body: T) : RequestResult<T>()
    class Error(val message: StringResource) : RequestResult<Nothing>()
}

