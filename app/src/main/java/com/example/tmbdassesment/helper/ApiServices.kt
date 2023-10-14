package com.example.tmbdassesment.helper

import com.example.tmbdassesment.model.ResponseJsonArray
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("discover/movie?api_key=${Constant.API_KEY}")
    fun getListMovie(): Call<JsonArray>

    @GET("movie/{id}?api_key=${Constant.API_KEY}")
    fun getReportMachine(
        @Path("id") type: String,
    ): Call<JsonElement>
}