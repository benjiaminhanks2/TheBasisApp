package com.example.thebasiscardapplication.dataModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DataProvider {


    /*
    * Method for getting the data from server and set to the live data
    * */
    fun getDataList(
        application: Application,
        cardData: MutableLiveData<CardDataModel>
    ) {
        val service = ServiceCreator.createService(application)
        val call = service.getDataList()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.printStackTrace()
                Log.e("onFailure", "hai")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val stringResponse = response.body()
                        val value = StringBuilder(stringResponse!!).deleteCharAt(0).toString()
                        val jsonObject = JSONObject(value)
                        val jsonArray = jsonObject.getJSONArray("data")
                        val list = ArrayList<CardDataList>()
                        for (i in 0 until jsonArray.length()) {
                            val dataObject = jsonArray.getJSONObject(i)
                            list.add(
                                CardDataList(
                                    dataObject.getString("id"),
                                    dataObject.getString("text")
                                )
                            )
                        }
                        val cardDataModel = CardDataModel(list)
                        cardData.value = cardDataModel
                    }
                }
            }
        })
    }
}

