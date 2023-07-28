package com.example.hackathonwinnersapp.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OrderDataModel(
    @Expose
    @SerializedName(value = "todo")
    val name: String?,

    @Expose
    @SerializedName(value = "todo")
    val status: String?,
)
