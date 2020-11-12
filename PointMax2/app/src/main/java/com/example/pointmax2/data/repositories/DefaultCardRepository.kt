package com.example.pointmax2.data.repositories

import androidx.lifecycle.LiveData
import com.example.pointmax2.data.database.CardDao
import com.example.pointmax2.data.database.CardRoomDatabase
import com.example.pointmax2.data.database.entities.CardItem

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class DefaultCardRepository(private val database: CardRoomDatabase): CardRepository {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    override fun observeAllCards(): LiveData<List<CardItem>>{
        return database.cardDao().getAllCards()
    }

    override fun getSpecificCard(name: String) : LiveData<CardItem> {
        return database.cardDao().getSpecificCard(name)
    }

    override suspend fun upsert(card: CardItem) {
        database.cardDao().upsert(card)
    }

    override suspend fun deleteByName(name: String){
        database.cardDao().deleteByName(name)
    }

    override suspend fun deleteAll() {
        database.cardDao().deleteAll()
    }

}