package com.example.hackathonwinnersapp.data.network.api

import com.example.hackathonwinnersapp.data.network.models.TaxesResponse
import com.example.hackathonwinnersapp.data.network.models.order.OrderDataModel
import com.example.hackathonwinnersapp.data.network.models.order.OrdersResponse
import com.example.hackathonwinnersapp.domain.models.orders.OrderRequestModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("/to.do")
    suspend fun getAllOrders(): OrdersResponse

    @POST("")
    suspend fun addOrder(
        @Body orderModel: OrderRequestModel
    ): OrderDataModel

//    changeOrderStatus(order(id, employee, status)) - создание, назначение, отмена, оплата

    @GET("/to.do/{id, info: String}")
    suspend fun getInfoByEmployee(): OrdersResponse // заказы, налоги, выплаты

    @GET("/too.doo")
    suspend fun getAllTaxes(): TaxesResponse

    // changeTaxStatus(order(id, employee, status)) - создание, назначение, отмена, оплата

    // getEmployees()


}