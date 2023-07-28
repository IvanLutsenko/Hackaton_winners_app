package com.example.hackathonwinnersapp.domain.Enums

enum class Status {
    NEW,
    IN_PROGRESS,
    PAYED,
    CANCELLED,
    UNIDENTIFIED;

    companion object {
        fun getType(typeName: String): Status {
            return when (typeName) {
                "new" -> NEW
                "in_progress" -> IN_PROGRESS
                "payed" -> PAYED
                "cancelled" -> CANCELLED
                else -> UNIDENTIFIED
            }
        }
    }
}