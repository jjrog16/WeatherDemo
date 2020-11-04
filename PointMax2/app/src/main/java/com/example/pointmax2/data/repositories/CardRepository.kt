package com.example.pointmax2.data.repositories

import androidx.lifecycle.LiveData
import com.example.pointmax2.data.database.CardDao
import com.example.pointmax2.data.database.CardRoomDatabase
import com.example.pointmax2.data.database.entities.CardItem

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class CardRepository(private val database: CardRoomDatabase) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allCards: LiveData<List<CardItem>> = database.cardDao().getCards()


    suspend fun insert(card: CardItem) {
        database.cardDao().insert(card)
    }

    suspend fun deleteByName(name: String){
        database.cardDao().deleteByName(name)
    }

}