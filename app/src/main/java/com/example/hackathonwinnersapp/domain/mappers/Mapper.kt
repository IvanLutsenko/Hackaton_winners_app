package com.example.hackathonwinnersapp.domain.mappers

import com.example.hackathonwinnersapp.data.network.models.OrdersResponse
import com.example.hackathonwinnersapp.domain.Enums.Status
import com.example.hackathonwinnersapp.domain.models.MappedOrderResponse
import com.example.hackathonwinnersapp.domain.models.OrderDomainModel

fun OrdersResponse.toDomain(): MappedOrderResponse {
    val mappedOrders = mutableListOf<OrderDomainModel>()
    orders?.forEach { order ->
        mappedOrders.add(
            OrderDomainModel(
                name = order.name.orEmpty(),
                status = Status.getType(order.status.orEmpty())
            )
        )
    }

    return MappedOrderResponse(
        orders = mappedOrders
    )
}