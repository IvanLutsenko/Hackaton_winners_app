package com.example.hackathonwinnersapp.util

import com.example.hackathonwinnersapp.data.utils.NetworkCallResult
import com.example.hackatonwinnersapp.R


inline fun <Body, Result> NetworkCallResult<Body>.handle(
    onSuccess: (Body) -> Result,
    onFailed: (NetworkCallResult.Error) -> Result
) = when (this) {
    is NetworkCallResult.Error -> onFailed(this)
    is NetworkCallResult.Success -> onSuccess(value)
}

inline fun <Body, Result> NetworkCallResult<Body>.onSuccess(
    func: (Body) -> Result
) {
    if (this is NetworkCallResult.Success) func(value)
}

val NetworkCallResult.Error.message
    get() = when (this) {
        is NetworkCallResult.Error.General -> strRes(message)
        NetworkCallResult.Error.Network -> strRes(R.string.network_error)
        is NetworkCallResult.Error.Unknown -> strRes(R.string.unknown_error)
    }