package com.example.thebasiscardapplication.dataModel

import retrofit2.Call
import retrofit2.http.GET

interface Api {

    /*
    * Calling api method
    * */
    @GET("fjaqJ")
    fun getDataList(): Call<String>

}