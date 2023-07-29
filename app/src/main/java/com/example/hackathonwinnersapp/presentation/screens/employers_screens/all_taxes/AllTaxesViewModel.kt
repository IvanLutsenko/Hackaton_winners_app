package com.example.hackathonwinnersapp.presentation.screens.employers_screens.all_taxes

import androidx.lifecycle.viewModelScope
import com.example.hackathonwinnersapp.domain.interactors.Interactor
import com.example.hackathonwinnersapp.domain.models.taxes.TaxDomainModel
import com.example.hackathonwinnersapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllTaxesViewModel @Inject constructor(
    private val interactor: Interactor
) : BaseViewModel() {

    private val _taxes = MutableStateFlow<List<TaxDomainModel>>(emptyList())
    val taxes = _taxes.asStateFlow()

    private val mockTaxes = listOf(
        TaxDomainModel(
            name = "Нолог 1",
            isPayed = true,
            sum = "1050"
        ),
        TaxDomainModel(
            name = "Нолог 2",
            isPayed = true,
            sum = "2060"
        ),
        TaxDomainModel(
            name = "Нолог 4",
            isPayed = false,
            sum = "4070"
        ),
        TaxDomainModel(
            name = "Нолог 5",
            isPayed = true,
            sum = "5080"
        ),
        TaxDomainModel(
            name = "Нолог 6",
            isPayed = false,
            sum = "6090"
        )
    )

    init {
        viewModelScope.launch { getTaxes() }
    }

    private suspend fun getTaxes() {
        _taxes.value = mockTaxes

//        when (val request = interactor.getAllTaxes()) {
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
