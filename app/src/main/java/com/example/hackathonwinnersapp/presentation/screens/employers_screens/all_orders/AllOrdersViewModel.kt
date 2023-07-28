package com.example.hackathonwinnersapp.presentation.screens.employers_screens.all_orders

import androidx.lifecycle.viewModelScope
import com.example.hackathonwinnersapp.domain.interators.OrdersInteractor
import com.example.hackathonwinnersapp.domain.models.OrderDomainModel
import com.example.hackathonwinnersapp.domain.models.RequestResult
import com.example.hackathonwinnersapp.presentation.base.BaseViewModel
import com.example.hackathonwinnersapp.util.StringResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllOrdersViewModel @Inject constructor(
    private val ordersInteractor: OrdersInteractor
) : BaseViewModel() {

    private val _orders = MutableStateFlow<List<OrderDomainModel>>(emptyList())
    val orders = _orders.asStateFlow()

    private val _errorMessage = MutableStateFlow<StringResource?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        viewModelScope.launch { getOrders() }
    }

    private suspend fun getOrders() {
        when (val request = ordersInteractor.getAllOrders()) {
            is RequestResult.Success -> {
                _orders.value = request.body.orders
                _errorMessage.value = null
            }

            is RequestResult.Error ->
                _errorMessage.value = request.message
        }
    }


}