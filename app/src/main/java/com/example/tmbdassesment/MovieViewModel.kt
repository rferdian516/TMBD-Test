package com.example.tmbdassesment

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tmbdassesment.helper.ApiServices
import com.example.tmbdassesment.model.MovieListModel
import com.example.tmbdassesment.model.ResultModel
import com.example.tmbdassesment.network.ApiClient
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private val list: MutableLiveData<ArrayList<MovieListModel>> = MutableLiveData()
    private var tempList: ArrayList<MovieListModel> = ArrayList()

    fun fetchMovie(context: Context) {
        val api = ApiClient.build(context)!!.create(ApiServices::class.java)
        val fetchedPost = api.getListMovie()

        fetchedPost.enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                when(response.code()) {
                    200 -> {
                        Log.i("TAG","status code => ${response.code()}")
                        val parsedData = response.body()!!.asJsonObject

                        Log.d("TAG","status code => $parsedData")
                        val value = parsedData.getAsJsonArray("results")
                        val convertedData =
                            Gson().fromJson(value, Array<MovieListModel>::class.java).toList()

                        list.value = ArrayList(convertedData)
                        tempList.addAll(convertedData)
                    }
                    else -> list.value = ArrayList()
                }
            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                list.value = ArrayList()
            }
        })
    }

    fun posts(): LiveData<ArrayList<MovieListModel>> = list
}