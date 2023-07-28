package com.example.hackathonwinnersapp.data.network.api

import com.example.hackathonwinnersapp.data.network.models.OrdersResponse
import retrofit2.http.GET

interface OrdersApiService {

    @GET("/to.do")
    suspend fun getAllOrders(): OrdersResponse
}