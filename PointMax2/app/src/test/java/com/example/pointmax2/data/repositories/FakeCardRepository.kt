package com.example.pointmax2.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.pointmax2.data.database.entities.CardItem

class FakeCardRepository : CardRepository{

    // Fake database for testing
    private var cardItem = mutableListOf<CardItem>()

    // Observable items
    private val observableAllCardItems = MutableLiveData<List<CardItem>>(cardItem)

    private val observeCardItem = MutableLiveData<CardItem>(CardItem(cardName = ""))

    private fun refreshLiveData(){
        return observableAllCardItems.postValue(cardItem)
    }

    override fun observeAllCards(): LiveData<List<CardItem>> {
        return observableAllCardItems
    }

    override fun getSpecificCard(name: String): LiveData<CardItem> {
        cardItem.forEach {
            if(it.cardName == name) {
                observeCardItem.postValue(it)
                return observeCardItem
            }
        }
        return observeCardItem
    }

    override suspend fun upsert(card: CardItem) {
        val foundCard = getSpecificCard(card.cardName).value ?: CardItem(cardName = "")
        if(foundCard == CardItem(cardName = "")){
            cardItem.add(card)
            refreshLiveData()
        } else {
            deleteByName(foundCard.cardName)
            cardItem.add(card)
            refreshLiveData()
        }
    }

    override suspend fun deleteByName(name: String) {
        val foundCard = getSpecificCard(name).value
        cardItem.remove(foundCard)
        refreshLiveData()
    }

    override suspend fun deleteAll() {
        cardItem = mutableListOf<CardItem>()
    }
}