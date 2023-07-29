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

//    const taxSelectOptions = [
//    {
//        label: 'Пенсионные взносы',
//        value: 'Пенсионные взносы'
//    },
//    {
//        label: 'Взносы на обязательное мед.страхование',
//        value: 'Взносы на обязательное мед.страхование'
//    },
//    {
//        label: 'Подоходный налог',
//        value: 'Подоходный налог'
//    }
//    ]

    private val mockTaxes = listOf(
        TaxDomainModel(
            name = "Подоходный налог ",
            isPayed = true,
            sum = "5050606"
        ),
        TaxDomainModel(
            name = "Мед.страхование    ",
            isPayed = false,
            sum = "42000"
        ),
        TaxDomainModel(
            name = "Пенсионные взносы",
            isPayed = false,
            sum = "69000"
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
