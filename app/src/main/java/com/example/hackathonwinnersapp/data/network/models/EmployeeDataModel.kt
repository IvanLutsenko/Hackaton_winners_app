package com.example.hackathonwinnersapp.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EmployeeDataModel(
    @Expose
    @SerializedName(value = "todo")
    val id: Int?,

    @Expose
    @SerializedName(value = "todo")
    val name: String?
)
