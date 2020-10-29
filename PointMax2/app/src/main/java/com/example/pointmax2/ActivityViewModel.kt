package com.example.pointmax2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pointmax2.data.database.entities.CardEntity
import com.example.pointmax2.data.repositories.CardRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class ActivityViewModel(private val repository: CardRepository) : ViewModel(){

    // The external LiveData interface to the property is immutable, so only this class can modify
    val allCards = repository.allCards

    // Internally, we use a MutableLiveData to handle navigation to the selected cxa
    private val _navigateToSelectedCard = MutableLiveData<CardEntity>()

    // The external immutable LiveData for the navigation property
    val navigateToSelectedCard: LiveData<CardEntity>
        get() = _navigateToSelectedCard

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * When the card is clicked, set the [_navigateToSelectedCard] [MutableLiveData]
     * @param card The [Card] that was clicked on.
     */
    fun displayCardDetails(card: CardEntity) {
        _navigateToSelectedCard.value = card
    }

    /**
     * After the navigation has taken place, make sure navigateToSelectedProperty is set to null
     */
    fun displayCardDetailsComplete() {
        _navigateToSelectedCard.value = null
    }
}