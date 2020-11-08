package com.example.pointmax2.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pointmax2.data.database.entities.CardItem
import com.example.pointmax2.data.repositories.DefaultCardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PointMaxViewModel(private val repository: DefaultCardRepository) : ViewModel(){

    // The external LiveData interface to the property is immutable, so only this class can modify
    //val allCards = repository.observeAllCards()

    fun observeAllCards(): LiveData<List<CardItem>> {
        return repository.observeAllCards()
    }

/*
    // Internally, we use a MutableLiveData to handle navigation to the selected card
    private val _navigateToSelectedCard = MutableLiveData<CardItem>()

    // The external immutable LiveData for the navigation property
    val navigateToSelectedCard: LiveData<CardItem>
        get() = _navigateToSelectedCard

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
*/

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
     * Function for observing a specfic card from the Room database
     */
    fun getSpecificCard(cardName: String) = repository.getSpecificCard(cardName)
}