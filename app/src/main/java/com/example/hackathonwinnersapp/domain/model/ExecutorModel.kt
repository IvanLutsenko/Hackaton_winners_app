package com.example.hackathonwinnersapp.domain.model

data class ExecutorModel(
    val name: String,
    val status: ExecutorStatus
)

enum class ExecutorStatus {
    IN_PROGRESS,
    CANCELED
}