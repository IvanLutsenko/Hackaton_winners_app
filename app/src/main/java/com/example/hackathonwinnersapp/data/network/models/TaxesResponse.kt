package com.example.hackathonwinnersapp.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TaxesResponse(
    @Expose
    @SerializedName(value = "todo1")
    val taxes: List<TaxDataModel>?
)
