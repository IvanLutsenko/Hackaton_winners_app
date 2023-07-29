package com.example.hackathonwinnersapp.presentation.screens.employers_screens.all_executors

import androidx.lifecycle.viewModelScope
import com.example.hackathonwinnersapp.domain.interactors.Interactor
import com.example.hackathonwinnersapp.domain.models.executor.ExecutorDomainModel
import com.example.hackathonwinnersapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllExecutorsViewModel @Inject constructor(
    private val interactor: Interactor
) : BaseViewModel() {

    private val _executors = MutableStateFlow<List<ExecutorDomainModel>>(emptyList())
    val executors = _executors.asStateFlow()

    private val mockExecutors = listOf(
        ExecutorDomainModel(
            id = "1",
            name = "Айнур",
        ),
        ExecutorDomainModel(
            id = "2",
            name = "Татьяна",
        ),
        ExecutorDomainModel(
            id = "3",
            name = "Ольга",
        ),
        ExecutorDomainModel(
            id = "4",
            name = "Ержан",
        ),
        ExecutorDomainModel(
            id = "5",
            name = "Василий",
        ),
    )

    init {
        viewModelScope.launch { getExecutor() }
    }

    fun onTaxClick(executor: ExecutorDomainModel) = Unit

    fun onActionButtonClick(executor: ExecutorDomainModel) = Unit

    private suspend fun getExecutor() {
        _executors.value = mockExecutors

//        when (val request = interactor.getAllExecutors()) {
//            is RequestResult.Success -> {
//                _taxes.value = request.body.taxes
//                _errorMessage.value = null
//            }
//
//            is RequestResult.Error ->
//                _errorMessage.value = request.message
//        }
    }
}
