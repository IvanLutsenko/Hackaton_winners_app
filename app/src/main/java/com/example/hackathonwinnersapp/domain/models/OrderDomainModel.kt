package com.example.hackathonwinnersapp.domain.models

import com.example.hackathonwinnersapp.domain.Enums.Status

data class OrderDomainModel(
    val name: String,
    val status: Status
)
