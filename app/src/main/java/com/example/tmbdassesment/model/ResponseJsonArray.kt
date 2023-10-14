package com.example.tmbdassesment.model

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

data class ResponseJsonArray(
    @SerializedName("error") var error: Boolean,
    @SerializedName("data") var data: JsonArray
)
