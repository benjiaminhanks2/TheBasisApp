package com.example.thebasiscardapplication

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.thebasiscardapplication.dataModel.CardDataList
import com.example.thebasiscardapplication.dataModel.CardDataModel
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_activity.*

class MainActivity : AppCompatActivity(), CardStackListener {

    lateinit var cardListViewModel: CardListViewModel
    lateinit var cardStackLayoutManager: CardStackLayoutManager
    var cardViewAdapter = CardViewAdapter()
    var cardList = ArrayList<CardDataList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpCardViews()
        initViewModels()
        callApi()
    }

    /*
    * Set up card view
    * */
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

    /*
    * Call api from viewModel
    * */
    private fun callApi() {
        showProgressBar()
        cardListViewModel.setDataToCard()
    }

    /*
    * View Model Initialize
    * */
    fun initViewModels() {
        cardListViewModel = ViewModelProviders.of(this).get(CardListViewModel::class.java)
        cardListViewModel.getDataFromObserver().observe(this,
            Observer<CardDataModel> { t ->
                hideProgressBar()
                if (t != null) {
                    responseSuccess(t)
                } else {
                    responseFailed()
                }
            })
    }

    /*
    * Api Response Failed
    * */
    private fun responseFailed() {
        Toast.makeText(this, "Response Failed", Toast.LENGTH_SHORT).show()
    }

    /*
    * Api response success method
    * */
    fun responseSuccess(cardDataModel: CardDataModel) {
        if (cardDataModel.dataList.isNotEmpty()) {
            cardList.clear()
            cardList.addAll(cardDataModel.dataList)
            setCardViewAdapter()
        }
    }

    /*
    * Set Card Adapter
    * */
    fun setCardViewAdapter() {
        cardViewAdapter.setCardDataList(cardList)
    }

    /*
    * Show progress bar
    * */
    fun showProgressBar() {
        circleProgressBar.visibility = View.VISIBLE
    }

    /*
    * hide progress bar
    * */
    fun hideProgressBar() {
        circleProgressBar.visibility = View.GONE
    }

    /*
    * Card Stack listener overridden methods
    * */
    override fun onCardDisappeared(view: View?, position: Int) {}

    override fun onCardDragging(direction: Direction?, ratio: Float) {}

    override fun onCardSwiped(direction: Direction?) {
        if (cardStackLayoutManager.topPosition == cardViewAdapter.itemCount) {
            setCardViewAdapter()
        }
    }

    override fun onCardCanceled() {}

    override fun onCardAppeared(view: View?, position: Int) {}

    override fun onCardRewound() {}

}
