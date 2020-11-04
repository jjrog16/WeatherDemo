package com.example.pointmax2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pointmax2.ui.PointMaxViewModel
import com.example.pointmax2.R
import com.example.pointmax2.data.database.entities.CardItem
import kotlinx.android.synthetic.main.card_item.view.*
import timber.log.Timber

class CardAdapter: RecyclerView.Adapter<CardAdapter.CardViewHolder>(){

    inner class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    // Implement DiffUtil for notifying when single elements change
    private val differCallBack = object : DiffUtil.ItemCallback<CardItem>(){
        override fun areItemsTheSame(oldItem: CardItem, newItem: CardItem): Boolean {
            return oldItem.cardName == oldItem.cardName
        }

        override fun areContentsTheSame(oldItem: CardItem, newItem: CardItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.card_item,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = differ.currentList[position]
        holder.itemView.apply {
            tv_card.text = card.cardName
            setOnClickListener {
                Timber.i("$card selected")
                onItemClickListener?.let {
                    it(card)
                }
            }
        }
    }

    private var onItemClickListener: ((CardItem) -> Unit) ?= null

    fun setOnItemClickListener(listener: (CardItem) -> Unit){
        onItemClickListener = listener
    }
}