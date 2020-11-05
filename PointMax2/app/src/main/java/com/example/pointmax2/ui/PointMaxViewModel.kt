package com.example.pointmax2.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pointmax2.data.database.entities.CardItem
import com.example.pointmax2.data.repositories.CardRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PointMaxViewModel(private val repository: CardRepository) : ViewModel(){

    // The external LiveData interface to the property is immutable, so only this class can modify
    val allCards = repository.allCards

    // Internally, we use a MutableLiveData to handle navigation to the selected cxa
    private val _navigateToSelectedCard = MutableLiveData<CardItem>()

    // The external immutable LiveData for the navigation property
    val navigateToSelectedCard: LiveData<CardItem>
        get() = _navigateToSelectedCard

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
//
//    /**
//     * When the card is clicked, set the [_navigateToSelectedCard] [MutableLiveData]
//     * @param card The [CardItem] that was clicked on.
//     */
//    fun displayCardDetails(card: CardItem) {
//        _navigateToSelectedCard.value = card
//    }
//
//    /**
//     * After the navigation has taken place, make sure navigateToSelectedProperty is set to null
//     */
//    fun displayCardDetailsComplete() {
//        _navigateToSelectedCard.value = null
//    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun upsert(card: CardItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.upsert(card)
    }

    /**
     * Launching a new coroutine to delete the data in a non-blocking way
     */
    fun deleteByName(cardName: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteByName(cardName)
    }

    /**
     * Launching a new coroutine to retrieve a card in a non-blocking way
     */
    fun getSpecificCard(cardName: String) = repository.getSpecificCard(cardName)
}