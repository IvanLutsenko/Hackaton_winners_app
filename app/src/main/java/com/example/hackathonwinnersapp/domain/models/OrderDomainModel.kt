package com.example.hackathonwinnersapp.domain.models

import com.example.hackathonwinnersapp.domain.Enums.OrderStatus

data class OrderDomainModel(
    val name: String,
    val status: OrderStatus
)
