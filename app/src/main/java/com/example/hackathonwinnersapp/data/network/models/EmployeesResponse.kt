package com.example.hackathonwinnersapp.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EmployeesResponse(
    @Expose
    @SerializedName(value = "todo")
    val employees: List<EmployeeDataModel>?
)
