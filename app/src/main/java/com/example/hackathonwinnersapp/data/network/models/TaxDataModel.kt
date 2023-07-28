package com.example.hackathonwinnersapp.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TaxDataModel(

    @Expose
    @SerializedName(value = "todo")
    val name: String?,

    @Expose
    @SerializedName(value = "todo")
    val isPayed: Boolean?,

    @Expose
    @SerializedName(value = "todo")
    val employees: List<EmployeeDataModel>,

    @Expose
    @SerializedName(value = "todo")
    val sum: Int?,


    )
