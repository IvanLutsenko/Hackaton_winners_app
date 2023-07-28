package com.example.hackathonwinnersapp.domain.interators

import com.example.hackathonwinnersapp.domain.models.RequestResult
import com.example.hackathonwinnersapp.domain.repositories.OrdersRepository
import com.example.hackathonwinnersapp.util.handle
import com.example.hackathonwinnersapp.util.message
import javax.inject.Inject

class OrdersInteractor @Inject constructor(
    private val repository: OrdersRepository
) {
    suspend fun getAllOrders() = repository.getAllOrders().handle(
        onSuccess = { RequestResult.Success(it) },
        onFailed = { RequestResult.Error(it.message) }
    )
}