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
        viewModel.allCards.observe(viewLifecycleOwner, Observer { cards ->
            cards?.let {
                cardAdapter.differ.submitList(it)
            }

        })

        // Observe the navigateToSelectedCard LiveData and Navigate when it isn't null
        // After navigating, call displayCardDetailsComplete() so that the ViewModel is ready
        // for another navigation event
        viewModel.navigateToSelectedCard.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                // Must find the NavController from the Fragment
                this.findNavController()
                        .navigate(WalletFragmentDirections.actionNavigationWalletToNavigationAddCustomCardFragment())

            }
        })
    }

    private fun setUpRecyclerView(){
        cardAdapter = CardAdapter()
        rv_wallet.apply {
            adapter = cardAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}