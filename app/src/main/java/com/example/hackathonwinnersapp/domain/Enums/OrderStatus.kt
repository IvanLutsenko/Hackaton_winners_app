package com.example.hackathonwinnersapp.domain.Enums

enum class OrderStatus {
    NEW,
    IN_PROGRESS,
    FULFILLED,
    PAYED,
    CANCELLED,
    UNIDENTIFIED;

    companion object {
        fun getType(typeName: String): OrderStatus {
            return when (typeName) {
                "new" -> NEW
                "in_progress" -> IN_PROGRESS
                "fulfilled" -> FULFILLED
                "payed" -> PAYED
                "cancelled" -> CANCELLED
                else -> UNIDENTIFIED
            }
        }

        fun getName(status: OrderStatus): String {
            return when (status) {
                NEW -> "Новый"
                IN_PROGRESS -> "В работе"
                FULFILLED -> "Выполнен"
                PAYED -> "Оплачен"
                CANCELLED -> "Отменен"
                UNIDENTIFIED -> "ашипка :("
            }
        }
    }
}