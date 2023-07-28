package com.example.hackathonwinnersapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackathonwinnersapp.util.StringResource
import com.example.hackathonwinnersapp.util.strRes
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel :
    ViewModel() {

    private val _isInProgress = MutableStateFlow(false)
    private val _toastText = MutableStateFlow<StringResource>(strRes(""))
    private val _baseSideEffects = Channel<BaseSideEffect>(Channel.BUFFERED)

    val isInProgress = _isInProgress.asStateFlow()
    val toastText = _toastText.asStateFlow()
    val baseSideEffects = _baseSideEffects.receiveAsFlow()

    fun setProgress(inProgress: Boolean) {
        _isInProgress.value = inProgress
    }

    suspend fun setToastText(message: StringResource?) = viewModelScope.launch {
        message?.let { _baseSideEffects.send(BaseSideEffect.Toast(message)) }
    }

    suspend fun onError(throwable: Throwable) = viewModelScope.launch {
        setToastText(strRes(throwable.localizedMessage ?: ""))
    }
}
