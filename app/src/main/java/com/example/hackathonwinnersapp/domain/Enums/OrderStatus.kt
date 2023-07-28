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
                "fullfiled" -> FULFILLED
                "payed" -> PAYED
                "cancelled" -> CANCELLED
                else -> UNIDENTIFIED
            }
        }
    }
}