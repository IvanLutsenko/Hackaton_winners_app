package com.example.hackathonwinnersapp.domain.models.orders

import com.example.hackathonwinnersapp.data.network.models.EmployeeDataModel
import com.example.hackathonwinnersapp.domain.Enums.OrderStatus

data class OrderDomainModel(
    val id: Int,
    val name: String,
    val employee: EmployeeDataModel,
    val sum: Int,
    val status: OrderStatus,
)