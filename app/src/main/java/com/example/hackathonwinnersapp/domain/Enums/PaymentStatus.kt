package com.example.hackathonwinnersapp.domain.Enums

enum class PaymentStatus {
    NEW,
    PAYED,
    UNIDENTIFIED;

    companion object {
        fun getType(typeName: String): PaymentStatus {
            return when (typeName) {
                "new" -> NEW
                "payed" -> PAYED
                else -> UNIDENTIFIED
            }
        }
    }
}