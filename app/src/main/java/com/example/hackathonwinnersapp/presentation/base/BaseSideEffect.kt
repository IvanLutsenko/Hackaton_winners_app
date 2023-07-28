package com.example.hackathonwinnersapp.presentation.base

import com.example.hackathonwinnersapp.util.StringResource

sealed class BaseSideEffect {

    data class Toast(val message: StringResource) : BaseSideEffect()
}
