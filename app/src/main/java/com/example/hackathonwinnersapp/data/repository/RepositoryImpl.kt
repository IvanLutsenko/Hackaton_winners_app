package com.example.hackathonwinnersapp.data.repository

import com.example.hackathonwinnersapp.data.network.api.ApiService
import com.example.hackathonwinnersapp.data.utils.NetworkCallResult
import com.example.hackathonwinnersapp.data.utils.safeApiCall
import com.example.hackathonwinnersapp.di.annotations.IoDispatcher
import com.example.hackathonwinnersapp.domain.mappers.toDomain
import com.example.hackathonwinnersapp.domain.models.orders.MappedOrderResponse
import com.example.hackathonwinnersapp.domain.models.taxes.MappedTaxesResponse
import com.example.hackathonwinnersapp.domain.repositories.Repository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : Repository {
    override suspend fun getAllOrders(): NetworkCallResult<MappedOrderResponse> = safeApiCall(
        dispatcher = dispatcher,
        onNullValue = { NetworkCallResult.Error.Unknown() },
        apiCall = { apiService.getAllOrders().toDomain() }
    )


    override suspend fun getAllTaxes(): NetworkCallResult<MappedTaxesResponse> = safeApiCall(
        dispatcher = dispatcher,
        onNullValue = { NetworkCallResult.Error.Unknown() },
        apiCall = { apiService.getAllTaxes().toDomain() }
    )
}