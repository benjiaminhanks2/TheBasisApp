package com.example.thebasiscardapplication.dataModel

import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface Api {


    /*
    * Calling api method
    * */
    @GET("fjaqJ")
    fun getDataList(): Call<String>

}