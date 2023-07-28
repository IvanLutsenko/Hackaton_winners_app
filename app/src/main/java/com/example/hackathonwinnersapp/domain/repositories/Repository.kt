package com.example.hackathonwinnersapp.domain.repositories

import com.example.hackathonwinnersapp.data.utils.NetworkCallResult
import com.example.hackathonwinnersapp.domain.models.orders.MappedOrderResponse
import com.example.hackathonwinnersapp.domain.models.taxes.MappedTaxesResponse

interface Repository {

    suspend fun getAllOrders(): NetworkCallResult<MappedOrderResponse>

    suspend fun getAllTaxes(): NetworkCallResult<MappedTaxesResponse>
}