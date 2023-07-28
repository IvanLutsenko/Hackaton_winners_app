package com.example.hackathonwinnersapp.data.network.api

import com.example.hackathonwinnersapp.data.network.models.OrdersResponse
import com.example.hackathonwinnersapp.data.network.models.TaxesResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/to.do")
    suspend fun getAllOrders(): OrdersResponse

    // changeOrderStatus(order(id, employee, status)) - создание, назначение, отмена, оплата

    @GET("/to.do/{id, info: String}")
    suspend fun getInfoByEmployee(): OrdersResponse // заказы, налоги, выплаты

    @GET("/too.doo")
    suspend fun getAllTaxes(): TaxesResponse

    // changeTaxStatus(order(id, employee, status)) - создание, назначение, отмена, оплата

    // getEmployees()


}