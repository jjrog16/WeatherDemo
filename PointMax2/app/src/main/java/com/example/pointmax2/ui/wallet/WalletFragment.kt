package com.example.pointmax2.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pointmax2.PointMaxViewModel
import com.example.pointmax2.R
import com.example.pointmax2.other.CardAdapter
import kotlinx.android.synthetic.main.fragment_wallet.*

class WalletFragment : Fragment() {

    private lateinit var viewModel: PointMaxViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wallet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = CardAdapter(listOf())

        viewModel = activity?.run {
            ViewModelProvider(this).get(PointMaxViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        rv_wallet.layoutManager = activity.run { LinearLayoutManager(this) }
        rv_wallet.adapter = adapter

        viewModel.allCards.observe(viewLifecycleOwner, Observer {
            adapter.cards = it
            adapter.notifyDataSetChanged()
        })

    }
}