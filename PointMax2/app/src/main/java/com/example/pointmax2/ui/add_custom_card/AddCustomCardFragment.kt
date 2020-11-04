package com.example.pointmax2.ui.add_custom_card

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pointmax2.ui.PointMaxViewModel
import com.example.pointmax2.R
import com.example.pointmax2.data.database.entities.CardItem
import com.example.pointmax2.ui.PointMaxActivity
import kotlinx.android.synthetic.main.fragment_add_custom_card.*
import timber.log.Timber

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

//        viewModel = activity?.run{
//            ViewModelProvider(this).get(PointMaxViewModel::class.java)
//        } ?: throw Exception("Invalid Activity")

        // Initialize the view with the card selected
        viewModel.navigateToSelectedCard.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                et_new_card_name.setText(it.cardName)
                et_generalEarn.setText(it.general.toString())
                et_airlinesEarn.setText(it.airlines.toString())
                et_restaurantsEarn.setText(it.restaurants.toString())
                et_travelEarn.setText(it.travel.toString())
                et_groceriesEarn.setText(it.groceries.toString())
                et_gasEarn.setText(it.gas.toString())
                viewModel.displayCardDetailsComplete()
            }
        })

        viewModel.allCards.observe(viewLifecycleOwner, Observer { cardList ->

            var isCardInList: Boolean = cardList.contains(CardItem(et_new_card_name.text.toString()))
            val valuesBeforeDonePressed = arrayOf(
                    et_new_card_name.text.toString().toUpperCase(),
                    et_generalEarn.text.toString().toDouble(),
                    et_airlinesEarn.text.toString().toDouble(),
                    et_restaurantsEarn.text.toString().toDouble(),
                    et_groceriesEarn.text.toString().toDouble(),
                    et_travelEarn.text.toString().toDouble(),
                    et_gasEarn.text.toString().toDouble()
            )
            bt_done.setOnClickListener {
                Timber.i("Done Pressed")
                val valuesAfterDonePressed = arrayOf(
                        et_new_card_name.text.toString().toUpperCase(),
                        et_generalEarn.text.toString().toDouble(),
                        et_airlinesEarn.text.toString().toDouble(),
                        et_restaurantsEarn.text.toString().toDouble(),
                        et_groceriesEarn.text.toString().toDouble(),
                        et_travelEarn.text.toString().toDouble(),
                        et_gasEarn.text.toString().toDouble()
                )
                val isBeforeAndAfterEqual = valuesBeforeDonePressed.contentDeepEquals(valuesAfterDonePressed)

                val action = AddCustomCardFragmentDirections.actionNavigationAddCustomCardFragmentToNavigationWallet()
                findNavController().navigate(action)

                /*when {
                    // If card has no name or has spaces, do not add it
                    TextUtils.isEmpty(et_new_card_name.text) or
                            et_new_card_name.text.toString().trim().matches("\\s*".toRegex()) -> {
                        Toast.makeText(
                                context,
                                getString(R.string.empty_not_saved),
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                    // If no changes were made to the card, return to the previous screen and make no changes
                    isBeforeAndAfterEqual -> {
                        val action = AddCustomCardFragmentDirections.actionNavigationAddCustomCardFragmentToNavigationWallet()
                        findNavController().navigate(action)
                    }

                    else -> {
                        // Otherwise, take inputs and enter into the database
                        viewModel.insert(
                                CardItem(
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
                }*/
            }
        })
    }

    // Called after the operation is completed in order to hide the keyboard when EditText field
    // is gone.
    private fun hideKeyboard(context: Context, editText: EditText) {
        val imm: InputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }
}