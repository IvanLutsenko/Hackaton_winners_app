package com.example.hackathonwinnersapp.domain.mappers

import com.example.hackathonwinnersapp.data.network.models.EmployeeDataModel
import com.example.hackathonwinnersapp.data.network.models.TaxesResponse
import com.example.hackathonwinnersapp.data.network.models.order.OrderDataModel
import com.example.hackathonwinnersapp.data.network.models.order.OrdersResponse
import com.example.hackathonwinnersapp.domain.Enums.OrderStatus
import com.example.hackathonwinnersapp.domain.models.orders.MappedOrderResponse
import com.example.hackathonwinnersapp.domain.models.orders.OrderDomainModel
import com.example.hackathonwinnersapp.domain.models.taxes.MappedTaxesResponse
import com.example.hackathonwinnersapp.domain.models.taxes.TaxDomainModel
import com.example.hackathonwinnersapp.util.orEmptyField

fun OrdersResponse.toDomain(): MappedOrderResponse {
    val mappedOrders = mutableListOf<OrderDomainModel>()

    orders?.forEach { order ->
        mappedOrders.add(
            OrderDomainModel(
                id = order.id.orEmptyField(),
                name = order.name.orEmptyField(),
                employee = order.employee ?: EmployeeDataModel(id = 0, name = "not available"),
                status = OrderStatus.getType(order.status.orEmpty()),
                sum = order.sum.orEmptyField()
            )
        )
    }

    return MappedOrderResponse(
        orders = mappedOrders
    )
}

fun TaxesResponse.toDomain(): MappedTaxesResponse {
    val mappedTaxes = mutableListOf<TaxDomainModel>()

    taxes?.forEach { tax ->
        mappedTaxes.add(
            TaxDomainModel(
                name = tax.name.orEmptyField(),
                isPayed = tax.isPayed ?: false,
                sum = tax.sum.orEmptyField().toString()
            )
        )
    }

    return MappedTaxesResponse(
        taxes = mappedTaxes
    )
}

fun OrderDataModel.toDomain(): OrderDomainModel {
    return OrderDomainModel(
        id = this.id.orEmptyField(),
        name = this.name.orEmpty(),
        employee = EmployeeDataModel(
            id = this.employee?.id.orEmptyField(),
            name = this.name.orEmpty()
        ),
        sum = this.sum.orEmptyField(),
        status = OrderStatus.getType(this.status.orEmpty())
    )
}