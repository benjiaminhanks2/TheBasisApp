package com.example.thebasiscardapplication

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thebasiscardapplication.dataModel.CardDataList
import kotlinx.android.synthetic.main.card_view_item.view.*
import java.util.*

class CardViewAdapter : RecyclerView.Adapter<CardViewAdapter.ViewHolder>() {

    var cardList = emptyList<CardDataList>()


    fun setCardDataList(cardList: List<CardDataList>) {
        this.cardList = cardList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.card_view_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.apply {
            val rnd = Random()
            val color = Color.argb(255, rnd.nextInt(100), rnd.nextInt(100), rnd.nextInt(100))
            textTitle.text = cardList[position].text
            textTitle.setTextColor(color)
            val type =
                Typeface.createFromAsset(textTitle.context.assets, "fonts/roboto_regular.ttf")
            textTitle.typeface = type
        }

    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun setStrings(cardList: List<CardDataList>) {
        this.cardList = cardList
    }

    fun getStrings(): List<CardDataList> {
        return cardList
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textTitle = view.textTitle

    }

}