package com.example.pointmax2.other

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pointmax2.R
import com.example.pointmax2.data.database.entities.CardItem
import kotlinx.android.synthetic.main.card_item.view.*

class CardAdapter(
        var cards: List<CardItem>
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val currentCardItem = cards[position]

        holder.itemView.tv_card.text = currentCardItem.cardName
    }

    inner class CardViewHolder(cardView: View) : RecyclerView.ViewHolder(cardView)
}