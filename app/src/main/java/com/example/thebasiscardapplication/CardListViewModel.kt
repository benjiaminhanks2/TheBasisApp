package com.example.thebasiscardapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.thebasiscardapplication.dataModel.CardDataModel
import com.example.thebasiscardapplication.dataModel.DataProvider


/*
* Cart List Data View Model
* */
class CardListViewModel(internal val application: Application) : AndroidViewModel(application) {

    val cardLiveData = MutableLiveData<CardDataModel>()

    /*
    * Getting data from Data Provider Class
    * */
    fun setDataToCard() {
        DataProvider.getDataList(application, cardLiveData)
    }

    /*
    * Returning live data observer
    * */
    fun getDataFromObserver(): MutableLiveData<CardDataModel> {
        return cardLiveData
    }

}