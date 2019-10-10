package com.example.thebasiscardapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.thebasiscardapplication.dataModel.CardDataModel
import com.example.thebasiscardapplication.dataModel.DataProvider

class CardListViewModel(internal val application: Application) : AndroidViewModel(application) {

    val cardLiveData = MutableLiveData<CardDataModel>()


    fun setDataToCard() {
        DataProvider.getDataList(application, cardLiveData)
    }


    fun getDataFromObserver(): MutableLiveData<CardDataModel> {
        return cardLiveData
    }

}