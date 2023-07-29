package com.example.hackathonwinnersapp.data.network.models.order

import com.example.hackathonwinnersapp.data.network.models.EmployeeDataModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OrderDataModel(
    @Expose
    @SerializedName(value = "todo1")
    val id: Int?,

    @Expose
    @SerializedName(value = "todo2")
    val name: String?,

    @Expose
    @SerializedName(value = "todo3")
    val employee: EmployeeDataModel?,

    @Expose
    @SerializedName(value = "todo4")
    val sum: Int?,

    @Expose
    @SerializedName(value = "todo5")
    val status: String?
)
