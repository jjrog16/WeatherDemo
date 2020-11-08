package com.example.pointmax2.ui.home

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pointmax2.ui.PointMaxViewModel
import com.example.pointmax2.R
import com.example.pointmax2.ui.PointMaxActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), PopupMenu.OnMenuItemClickListener {

    private lateinit var viewModel: PointMaxViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as PointMaxActivity).viewModel

        btn_category_selector.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), btn_category_selector)
            val menuInflater: MenuInflater = popupMenu.menuInflater
            popupMenu.setOnMenuItemClickListener(this)
            menuInflater.inflate(R.menu.card_selector_popup_menu, popupMenu.menu)
            popupMenu.show()
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {


        var result = false
        if (item != null) {
            viewModel.allCards.observe(viewLifecycleOwner, Observer {cardList ->
                val bestCardList = mutableMapOf<String, Double>()
                if(cardList.isEmpty()) {
                    Toast.makeText(context, "No cards in wallet", Toast.LENGTH_SHORT).show()
                    return@Observer
                } else {
                    bestCardList.clear()
                }
                when (item.itemId) {
                    R.id.generalCategory -> {
                        cardList.forEach {
                            bestCardList[it.cardName] = it.general
                        }
                        if (bestCardList.isNotEmpty()) {
                            val sorted = bestCardList.entries.sortedBy { it.value }
                            tv_top_card.text = sorted.last().key
                        }
                        result = true
                    }
                    R.id.groceriesCategory -> {
                        cardList.forEach {
                            bestCardList[it.cardName] = it.groceries
                        }
                        if (bestCardList.isNotEmpty()) {
                            val sorted = bestCardList.entries.sortedBy { it.value }
                            tv_top_card.text = sorted.last().key
                        }
                        result = true
                    }
                    R.id.restaurantsCategory -> {
                        cardList.forEach {
                            bestCardList[it.cardName] = it.restaurants
                        }
                        if (bestCardList.isNotEmpty()) {
                            val sorted = bestCardList.entries.sortedBy { it.value }
                            tv_top_card.text = sorted.last().key
                        }
                        result = true
                    }
                    R.id.gasCategory -> {
                        cardList.forEach {
                            bestCardList[it.cardName] = it.gas
                        }
                        if (bestCardList.isNotEmpty()) {
                            val sorted = bestCardList.entries.sortedBy { it.value }
                            tv_top_card.text = sorted.last().key
                        }
                        result = true
                    }
                    R.id.airlinesCategory -> {
                        cardList.forEach {
                            bestCardList[it.cardName] = it.airlines
                        }
                        if (bestCardList.isNotEmpty()) {
                            val sorted = bestCardList.entries.sortedBy { it.value }
                            tv_top_card.text = sorted.last().key
                        }
                        result = true
                    }
                    R.id.travelCategory -> {
                        cardList.forEach {
                            bestCardList[it.cardName] = it.travel
                        }
                        if (bestCardList.isNotEmpty()) {
                            val sorted = bestCardList.entries.sortedBy { it.value }
                            tv_top_card.text = sorted.last().key
                        }
                        result = true
                    }
                }
            })
        }
        return result
    }

    // Choose the best card
    fun chooseBestCard(item: String): String{
        var result = ""
        viewModel.allCards.observe(viewLifecycleOwner, Observer {cardList ->
            val bestCardList = mutableMapOf<String, Double>()
            if(cardList.isEmpty()) {
                Toast.makeText(context, "No cards in wallet", Toast.LENGTH_SHORT).show()
                return@Observer
            } else {
                bestCardList.clear()
            }
            when (item) {
                "General" -> {
                    cardList.forEach {
                        bestCardList[it.cardName] = it.general
                    }
                    if (bestCardList.isNotEmpty()) {
                        val sorted = bestCardList.entries.sortedBy { it.value }
                        //tv_top_card.text = sorted.last().key
                        result = sorted.last().key
                    }
                }
                "Groceries" -> {
                    cardList.forEach {
                        bestCardList[it.cardName] = it.groceries
                    }
                    if (bestCardList.isNotEmpty()) {
                        val sorted = bestCardList.entries.sortedBy { it.value }
                        //tv_top_card.text = sorted.last().key
                        result = sorted.last().key
                    }
                }
                "Restaurants" -> {
                    cardList.forEach {
                        bestCardList[it.cardName] = it.restaurants
                    }
                    if (bestCardList.isNotEmpty()) {
                        val sorted = bestCardList.entries.sortedBy { it.value }
                        //tv_top_card.text = sorted.last().key
                        result = sorted.last().key
                    }
                }
                "Gas" -> {
                    cardList.forEach {
                        bestCardList[it.cardName] = it.gas
                    }
                    if (bestCardList.isNotEmpty()) {
                        val sorted = bestCardList.entries.sortedBy { it.value }
                        //tv_top_card.text = sorted.last().key
                        result = sorted.last().key
                    }
                }
                "Airlines" -> {
                    cardList.forEach {
                        bestCardList[it.cardName] = it.airlines
                    }
                    if (bestCardList.isNotEmpty()) {
                        val sorted = bestCardList.entries.sortedBy { it.value }
                        //tv_top_card.text = sorted.last().key
                        result = sorted.last().key
                    }
                }
                "Travel" -> {
                    cardList.forEach {
                        bestCardList[it.cardName] = it.travel
                    }
                    if (bestCardList.isNotEmpty()) {
                        val sorted = bestCardList.entries.sortedBy { it.value }
                        //tv_top_card.text = sorted.last().key
                        result = sorted.last().key
                    }
                }
            }
        })
        return result
    }
}