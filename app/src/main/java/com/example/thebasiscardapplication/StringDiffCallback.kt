package com.example.thebasiscardapplication

import androidx.recyclerview.widget.DiffUtil
import com.example.thebasiscardapplication.dataModel.CardDataList


public class StringDiffCallback(
    private val old: List<CardDataList>,
    private val new: List<CardDataList>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition].id == new[newPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition] == new[newPosition]
    }

}