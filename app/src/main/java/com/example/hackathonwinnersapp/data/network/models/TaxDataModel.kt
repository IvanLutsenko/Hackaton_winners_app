package com.example.hackathonwinnersapp.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TaxDataModel(

    @Expose
    @SerializedName(value = "todo1")
    val name: String?,

    @Expose
    @SerializedName(value = "todo2")
    val isPayed: Boolean?,

    @Expose
    @SerializedName(value = "todo3")
    val employees: List<EmployeeDataModel>,

    @Expose
    @SerializedName(value = "todo4")
    val sum: Int?,


    )
