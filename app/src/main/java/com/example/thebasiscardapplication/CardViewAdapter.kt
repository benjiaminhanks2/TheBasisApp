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
import android.graphics.drawable.GradientDrawable


/*
* Card view adapter
* */
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
            val color = getDarkRandomColor()
            textTitle.text = cardList[position].text
            val type =
                Typeface.createFromAsset(textTitle.context.assets, "fonts/roboto_regular.ttf")
            textTitle.typeface = type

            val trackPosition = "${position + 1}/${cardList.size}"
            textId.text = trackPosition
            when (position % 4) {
                0 -> constraintCard.setBackgroundResource(R.drawable.card_view_gradient)
                1 -> constraintCard.setBackgroundResource(R.drawable.card_view_gradient_1)
                2 -> constraintCard.setBackgroundResource(R.drawable.card_view_gradient_3)
                3 -> constraintCard.setBackgroundResource(R.drawable.card_view_gradient_2)
            }

        }

    }

    override fun getItemCount(): Int {
        return cardList.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textTitle = view.textTitle
        val textId = view.textId
        val constraintCard = view.constraintCard

    }

    /*
    * Get Random Light color
    * */
    fun getDarkRandomColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(150), rnd.nextInt(100), rnd.nextInt(100))
    }

}