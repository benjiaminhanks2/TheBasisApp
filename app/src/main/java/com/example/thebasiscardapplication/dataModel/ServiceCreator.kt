package com.example.thebasiscardapplication.dataModel

import android.content.Context
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.converter.scalars.ScalarsConverterFactory


object ServiceCreator {

    /**
     * This function will return the service class for ApiServiceCreator
     */
    private fun <S> createService(serviceClass: Class<S>, context: Context): S {
        val httpClientBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClientBuilder.addInterceptor(httpLoggingInterceptor)
        val okHttpClient = httpClientBuilder.build()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://git.io/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
        val retrofit = retrofitBuilder.client(okHttpClient).build()
        return retrofit.create(serviceClass)
    }


    fun createService(context: Context): Api {
        return createService<Api>(Api::class.java, context)
    }

}