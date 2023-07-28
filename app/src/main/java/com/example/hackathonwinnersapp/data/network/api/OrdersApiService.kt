package com.example.hackathonwinnersapp.data.network.api

import com.example.hackathonwinnersapp.data.network.models.OrdersResponse
import retrofit2.http.GET

interface OrdersApiService {

    @GET("/to.do")
    suspend fun getAllOrders(): OrdersResponse

    // changeOrderStatus(order(id, employee, status)) - создание, назначение, отмена, оплата

    @GET("/to.do/{id, info: String}")
    suspend fun getInfoByEmployee(): OrdersResponse // заказы, налоги, выплаты

    // getAllTaxes()

    // changeTaxStatus(order(id, employee, status)) - создание, назначение, отмена, оплата

    // getEmployees()


}