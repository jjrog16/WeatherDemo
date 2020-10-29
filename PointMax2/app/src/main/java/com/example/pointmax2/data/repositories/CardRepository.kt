package com.example.pointmax2.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pointmax2.data.database.CardDao
import com.example.pointmax2.data.database.entities.CardEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class CardRepository(private val cardDao: CardDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allCards: LiveData<List<CardEntity>> = cardDao.getCards()


    suspend fun insert(card: CardEntity) {
        cardDao.insert(card)
    }

    suspend fun deleteByName(name: String){
        cardDao.deleteByName(name)
    }

}