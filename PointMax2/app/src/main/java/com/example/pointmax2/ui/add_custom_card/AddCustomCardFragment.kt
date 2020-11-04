package com.example.pointmax2.ui.add_custom_card

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.pointmax2.ui.PointMaxViewModel
import com.example.pointmax2.R
import com.example.pointmax2.data.database.entities.CardItem
import com.example.pointmax2.ui.PointMaxActivity
import kotlinx.android.synthetic.main.fragment_add_custom_card.*

class AddCustomCardFragment : Fragment(){

    private lateinit var viewModel: PointMaxViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_custom_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as PointMaxActivity).viewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val passedCard = AddCustomCardFragmentArgs.fromBundle(requireArguments()).cardItem

        initializeCustomCard(passedCard)

        viewModel.allCards.observe(viewLifecycleOwner, Observer { cardList ->

            bt_done.setOnClickListener {
                when {
                    // If card has no name or has spaces, do not add it
                    TextUtils.isEmpty(et_new_card_name.text) or
                            et_new_card_name.text.toString().trim().matches("\\s*".toRegex()) -> {
                        Toast.makeText(
                                context,
                                getString(R.string.empty_not_saved),
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        viewModel.insert(
                                CardItem(
                                        id = passedCard.id,
                                        cardName = et_new_card_name.text.toString().toUpperCase(),
                                        general = et_generalEarn.text.toString().toDouble(),
                                        airlines = et_airlinesEarn.text.toString().toDouble(),
                                        restaurants = et_restaurantsEarn.text.toString().toDouble(),
                                        groceries = et_groceriesEarn.text.toString().toDouble(),
                                        travel = et_travelEarn.text.toString().toDouble(),
                                        gas = et_gasEarn.text.toString().toDouble()
                                )
                        )

                        // Hides keyboard after finishing input
                        context?.let { it1 -> hideKeyboard(it1, et_new_card_name) }

                        val action = AddCustomCardFragmentDirections.actionNavigationAddCustomCardFragmentToNavigationWallet()
                        findNavController().navigate(action)
                    }
                }
            }

        })

        bt_delete.setOnClickListener{
            viewModel.deleteByName(passedCard.cardName)
            val action = AddCustomCardFragmentDirections.actionNavigationAddCustomCardFragmentToNavigationWallet()
            findNavController().navigate(action)
        }
    }

    // Called after the operation is completed in order to hide the keyboard when EditText field
    // is gone.
    private fun hideKeyboard(context: Context, editText: EditText) {
        val imm: InputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }

    private fun initializeCustomCard(passedCard: CardItem){
        et_new_card_name.setText(passedCard.cardName)
        et_generalEarn.setText(passedCard.general.toString())
        et_airlinesEarn.setText(passedCard.airlines.toString())
        et_restaurantsEarn.setText(passedCard.restaurants.toString())
        et_travelEarn.setText(passedCard.travel.toString())
        et_groceriesEarn.setText(passedCard.groceries.toString())
        et_gasEarn.setText(passedCard.gas.toString())
    }

}