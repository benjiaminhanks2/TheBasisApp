package com.example.thebasiscardapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.example.thebasiscardapplication.dataModel.CardDataList
import com.example.thebasiscardapplication.dataModel.CardDataModel
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CardStackListener {

    lateinit var cardListViewModel: CardListViewModel
    lateinit var cardStackLayoutManager: CardStackLayoutManager
    var cardViewAdapter = CardViewAdapter()
    var cardList = ArrayList<CardDataList>()
    var cardPosition = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpCardViews()
        initViewModels()
        callApi()
    }

    fun setUpCardViews() {
        cardStackLayoutManager = CardStackLayoutManager(this, this)
        cardStackLayoutManager.setStackFrom(StackFrom.None)
        cardStackLayoutManager.setVisibleCount(3)
        cardStackLayoutManager.setTranslationInterval(8.0f)
        cardStackLayoutManager.setScaleInterval(0.95f)
        cardStackLayoutManager.setSwipeThreshold(0.3f)
        cardStackLayoutManager.setMaxDegree(20.0f)
        cardStackLayoutManager.setDirections(Direction.HORIZONTAL)
        cardStackLayoutManager.setCanScrollHorizontal(true)
        cardStackLayoutManager.setCanScrollVertical(true)
        cardStackLayoutManager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        cardStackLayoutManager.setOverlayInterpolator(LinearInterpolator())
        cardStackView.layoutManager = cardStackLayoutManager
        cardStackView.adapter = cardViewAdapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    private fun callApi() {
        cardListViewModel.setDataToCard()
    }


    fun initViewModels() {
        cardListViewModel = ViewModelProviders.of(this).get(CardListViewModel::class.java)
        cardListViewModel.getDataFromObserver().observe(this,
            Observer<CardDataModel> { t ->
                Log.e("data", "$t")
                if (t != null) {
                    responseSuccess(t)
                } else {
                    responseFailed()
                }
            })
    }

    private fun responseFailed() {

    }


    fun responseSuccess(cardDataModel: CardDataModel) {
        if (cardDataModel.dataList.isNotEmpty()) {
            cardList.clear()
            cardList.addAll(cardDataModel.dataList)
            cardViewAdapter.setCardDataList(cardDataModel.dataList)
            setCardTrack(cardPosition)
        }
    }


    private fun paginate() {
        val old = cardViewAdapter.getStrings()
        val new = old.plus(cardList)
        val callback = StringDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        cardViewAdapter.setStrings(new)
        result.dispatchUpdatesTo(cardViewAdapter)
    }

    fun setCardTrack(cardPos: Int) {
        textId.text = "$cardPos"
    }


    override fun onCardDisappeared(view: View?, position: Int) {

    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {
        cardPosition = cardStackLayoutManager.topPosition + 1
        if (cardStackLayoutManager.topPosition == cardViewAdapter.itemCount) {
            paginate()
            cardPosition = 1
        }
        setCardTrack(cardPosition)
    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View?, position: Int) {
    }

    override fun onCardRewound() {
    }

}
