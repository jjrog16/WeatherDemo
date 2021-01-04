package com.example.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.data.models.Daily
import com.example.ui.weather.R
import kotlinx.android.synthetic.main.daily_item.view.*
import timber.log.Timber

class DailyAdapter: RecyclerView.Adapter<DailyAdapter.DailyViewHolder>(){

    inner class DailyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    // Implement DiffUtil for notifying when single elements change
    private val differCallBack = object : DiffUtil.ItemCallback<Daily>(){
        override fun areItemsTheSame(oldItem: Daily, newItem: Daily): Boolean {
            return oldItem.dt == oldItem.dt
        }

        override fun areContentsTheSame(oldItem: Daily, newItem: Daily): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        return DailyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.daily_item,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        val day = differ.currentList[position]
        holder.itemView.apply {
            tvDaily.text = day.dt.toString()
            setOnClickListener {
                onItemClickListener?.let {
                    Timber.i("Day tapped -> $day")
                    it(day)
                }
            }
        }
    }

    private var onItemClickListener: ((Daily) -> Unit) ?= null

    fun setOnItemClickListener(listener: (Daily) -> Unit){
        onItemClickListener = listener
    }
}