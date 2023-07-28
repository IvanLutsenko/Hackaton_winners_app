package com.example.hackathonwinnersapp.domain.mappers

import com.example.hackathonwinnersapp.data.network.models.OrdersResponse
import com.example.hackathonwinnersapp.domain.Enums.OrderStatus
import com.example.hackathonwinnersapp.domain.models.MappedOrderResponse
import com.example.hackathonwinnersapp.domain.models.OrderDomainModel

fun OrdersResponse.toDomain(): MappedOrderResponse {
    val mappedOrders = mutableListOf<OrderDomainModel>()
    orders?.forEach { order ->
        mappedOrders.add(
            OrderDomainModel(
                name = order.name.orEmpty(),
                status = OrderStatus.getType(order.status.orEmpty())
            )
        )
    }

    return MappedOrderResponse(
        orders = mappedOrders
    )
}