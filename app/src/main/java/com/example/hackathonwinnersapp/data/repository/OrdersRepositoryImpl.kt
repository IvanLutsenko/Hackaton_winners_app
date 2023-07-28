package com.example.hackathonwinnersapp.data.repository

import com.example.hackathonwinnersapp.data.network.api.OrdersApiService
import com.example.hackathonwinnersapp.data.utils.NetworkCallResult
import com.example.hackathonwinnersapp.data.utils.safeApiCall
import com.example.hackathonwinnersapp.di.annotations.IoDispatcher
import com.example.hackathonwinnersapp.domain.mappers.toDomain
import com.example.hackathonwinnersapp.domain.models.MappedOrderResponse
import com.example.hackathonwinnersapp.domain.repositories.OrdersRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class OrdersRepositoryImpl @Inject constructor(
    private val apiService: OrdersApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : OrdersRepository {
    override suspend fun getAllOrders(): NetworkCallResult<MappedOrderResponse> {
        //TODO remove mock when API is ready
//        val mockOrders = listOf(
//            OrderDomainModel(name = "заказ 1", status = Status.NEW),
//            OrderDomainModel(name = "заказ 2", status = Status.IN_PROGRESS),
//            OrderDomainModel(name = "заказ 3", status = Status.IN_PROGRESS),
//            OrderDomainModel(name = "заказ 5", status = Status.PAYED),
//            OrderDomainModel(name = "заказ 6", status = Status.PAYED),
//            OrderDomainModel(name = "заказ 7", status = Status.CANCELLED)
//        )
//        //return mockOrders

        return safeApiCall(
            dispatcher = dispatcher,
            onNullValue = { NetworkCallResult.Error.Unknown() },
            apiCall = { apiService.getAllOrders().toDomain() }
        )
    }

}