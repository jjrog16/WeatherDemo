package com.example.pointmax2.ui.add_custom_card

import android.content.Context
import android.os.Bundle
import android.text.Editable
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
        val passedCard = AddCustomCardFragmentArgs.fromBundle(requireArguments()).cardItem

        initializeCustomCard(passedCard)

        viewModel.allCards.observe(viewLifecycleOwner, Observer { cardList ->
            bt_done.setOnClickListener {
                val cardToBeEntered = et_new_card_name.text.toString().toUpperCase()
                if(checkIfFieldsValid()){
                    when {
                        // Check if there is already a card in the database and replace the card if
                        // there is a card in the database
                        isCardNameInList(cardList, cardToBeEntered) -> {
                            viewModel.getSpecificCard(cardToBeEntered).observe(viewLifecycleOwner, Observer { specificCard ->
                                cardToBeEntered.let { cardNameToBeEntered ->
                                    viewModel.upsert(
                                            CardItem(
                                                    id = specificCard.id,
                                                    cardName = cardNameToBeEntered,
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
                            })
                        }

                        // Insert the card
                        else -> {
                            viewModel.upsert(
                                    CardItem(
                                            id = passedCard.id,
                                            cardName = cardToBeEntered,
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
            }
        })

        bt_delete.setOnClickListener{
            viewModel.deleteByName(passedCard.cardName)
            val action = AddCustomCardFragmentDirections.actionNavigationAddCustomCardFragmentToNavigationWallet()
            findNavController().navigate(action)
        }
    }

    // If any fields are empty, show a toast
    private fun checkIfFieldsValid() : Boolean{
        when {
            isTextFieldEmpty(et_new_card_name.text) -> {
                fieldToast("Card Name")
                return false
            }
            isTextFieldEmpty(et_generalEarn.text) -> {
                fieldToast("General")
                return false
            }
            isTextFieldEmpty(et_airlinesEarn.text) -> {
                fieldToast("Airlines")
                return false
            }
            isTextFieldEmpty(et_restaurantsEarn.text) -> {
                fieldToast("Restaurants")
                return false
            }
            isTextFieldEmpty(et_travelEarn.text) -> {
                fieldToast("Travel")
                return false
            }
            isTextFieldEmpty(et_groceriesEarn.text) -> {
                fieldToast("Groceries")
                return false
            }
            isTextFieldEmpty(et_gasEarn.text) -> {
                fieldToast("Gas")
                return false
            }
        }
        return true
    }

    // Displays a Toast message for the empty field
    private fun fieldToast(emptyField: String){
        Toast.makeText(context,"Please enter a value for: $emptyField",Toast.LENGTH_SHORT).show()
    }

    // Called after the operation is completed in order to hide the keyboard when EditText field
    // is gone.
    private fun hideKeyboard(context: Context, editText: EditText) {
        val imm: InputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }

    // Start the AddCustomCardFragment with information passed in from navigation
    private fun initializeCustomCard(passedCard: CardItem){
        et_new_card_name.setText(passedCard.cardName)
        et_generalEarn.setText(passedCard.general.toString())
        et_airlinesEarn.setText(passedCard.airlines.toString())
        et_restaurantsEarn.setText(passedCard.restaurants.toString())
        et_travelEarn.setText(passedCard.travel.toString())
        et_groceriesEarn.setText(passedCard.groceries.toString())
        et_gasEarn.setText(passedCard.gas.toString())
    }

    // Check if the name about to be entered is already in the list of cards
    private fun isCardNameInList(cardList: List<CardItem>, cardNameEditText: String) : Boolean{
        cardList.forEach {
             return (it.cardName == cardNameEditText)
        }
        return false
    }

    // Returns if the editText field for the Card Name is left empty
    private fun isTextFieldEmpty(textField: Editable) : Boolean {
        return (TextUtils.isEmpty(textField.toString()) or textField.toString().trim().matches("\\s*".toRegex()))
    }
}