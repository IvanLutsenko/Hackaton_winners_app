package com.example.hackathonwinnersapp.domain.repositories

import com.example.hackathonwinnersapp.data.utils.NetworkCallResult
import com.example.hackathonwinnersapp.domain.models.MappedOrderResponse

interface OrdersRepository {

    suspend fun getAllOrders(): NetworkCallResult<MappedOrderResponse>
}