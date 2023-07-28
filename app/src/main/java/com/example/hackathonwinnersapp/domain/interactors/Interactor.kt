package com.example.hackathonwinnersapp.domain.interactors

import com.example.hackathonwinnersapp.domain.models.RequestResult
import com.example.hackathonwinnersapp.domain.models.orders.OrderRequestModel
import com.example.hackathonwinnersapp.domain.repositories.Repository
import com.example.hackathonwinnersapp.util.handle
import com.example.hackathonwinnersapp.util.message
import javax.inject.Inject

class Interactor @Inject constructor(
    private val repository: Repository
) {

    suspend fun getAllOrders() = repository.getAllOrders().handle(
        onSuccess = { RequestResult.Success(it) },
        onFailed = { RequestResult.Error(it.message) }
    )

    suspend fun getAllTaxes() = repository.getAllTaxes().handle(
        onSuccess = { RequestResult.Success(it) },
        onFailed = { RequestResult.Error(it.message) }
    )

    suspend fun addOrder(order: OrderRequestModel) = repository.addOrder(order).handle(
        onSuccess = { RequestResult.Success(it) },
        onFailed = { RequestResult.Error(it.message) }
    )
}