package com.example.pointmax2.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pointmax2.ui.PointMaxViewModel
import com.example.pointmax2.R
import com.example.pointmax2.adapters.CardAdapter
import com.example.pointmax2.ui.PointMaxActivity
import kotlinx.android.synthetic.main.fragment_wallet.*
import timber.log.Timber

class WalletFragment : Fragment() {
    private lateinit var viewModel: PointMaxViewModel
    private lateinit var cardAdapter: CardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as PointMaxActivity).viewModel

        setUpRecyclerView()

        // Load the cards into the adapter
        viewModel.observeAllCards().observe(viewLifecycleOwner, Observer { cards ->
            cards?.let {
                cardAdapter.differ.submitList(it)
            }
        })

        cardAdapter.setOnItemClickListener {
            // Must find the NavController from the Fragment
            findNavController().navigate(
                    WalletFragmentDirections.actionNavigationWalletToNavigationAddCustomCardFragment(it)
            )
        }
    }

    private fun setUpRecyclerView(){
        cardAdapter = CardAdapter()
        rv_wallet.apply {
            adapter = cardAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}