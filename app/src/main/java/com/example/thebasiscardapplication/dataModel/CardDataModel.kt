package com.example.thebasiscardapplication.dataModel

data class CardDataModel(
    val dataList: List<CardDataList>
)


data class CardDataList(
    val id: String,
    val text: String
)