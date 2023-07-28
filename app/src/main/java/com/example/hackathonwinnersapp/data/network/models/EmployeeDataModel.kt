package com.example.hackathonwinnersapp.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EmployeeDataModel(
    @Expose
    @SerializedName(value = "todo1")
    val id: Int?,

    @Expose
    @SerializedName(value = "todo2")
    val name: String?
)
