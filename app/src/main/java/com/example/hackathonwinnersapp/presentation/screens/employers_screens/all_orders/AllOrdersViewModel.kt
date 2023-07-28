package com.example.hackathonwinnersapp.presentation.screens.employers_screens.all_orders

import androidx.lifecycle.viewModelScope
import com.example.hackathonwinnersapp.data.network.models.EmployeeDataModel
import com.example.hackathonwinnersapp.domain.Enums.OrderStatus
import com.example.hackathonwinnersapp.domain.interators.OrdersInteractor
import com.example.hackathonwinnersapp.domain.models.OrderDomainModel
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

    val mockOrders = listOf(
        OrderDomainModel(
            id = 1,
            name = "заказ 1",
            employee = EmployeeDataModel(id = 1, name = "Василий"),
            sum = 100500,
            status = OrderStatus.NEW,
        ),
        OrderDomainModel(
            id = 2,
            name = "заказ 2",
            employee = EmployeeDataModel(id = 1, name = "Василий"),
            sum = 100500,
            status = OrderStatus.IN_PROGRESS,
        ),
        OrderDomainModel(
            id = 3,
            name = "заказ 4",
            employee = EmployeeDataModel(id = 1, name = "Василий"),
            sum = 100500,
            status = OrderStatus.FULFILLED,
        ),
        OrderDomainModel(
            id = 4,
            name = "заказ 5",
            employee = EmployeeDataModel(id = 1, name = "Василий"),
            sum = 100500,
            status = OrderStatus.PAYED,
        ),
        OrderDomainModel(
            id = 5,
            name = "заказ 7",
            employee = EmployeeDataModel(id = 1, name = "Василий"),
            sum = 100500,
            status = OrderStatus.CANCELLED
        )
    )

    init {
        viewModelScope.launch { getOrders() }
    }

    fun onOrderClick(order: OrderDomainModel) = Unit

    fun onEmployeeClick(employee: EmployeeDataModel) = Unit

    fun onActionButtonClick(order: OrderDomainModel) = Unit

    fun onErrorShown() = _errorMessage.value == null

    private suspend fun getOrders() {
        _orders.value = mockOrders
//        when (val request = ordersInteractor.getAllOrders()) {
//            is RequestResult.Success -> {
//                _orders.value = request.body.orders
//                _errorMessage.value = null
//            }
//
//            is RequestResult.Error ->
//                _errorMessage.value = request.message
//        }
    }


}