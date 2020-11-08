package com.example.pointmax2.data.repositories

import androidx.lifecycle.LiveData
import com.example.pointmax2.data.database.entities.CardItem

class FakeCardRepository : CardRepository{

    override fun observeAllCards(): LiveData<List<CardItem>> {
        TODO("Not yet implemented")
    }

    override fun getSpecificCard(name: String): LiveData<CardItem> {
        TODO("Not yet implemented")
    }

    override suspend fun upsert(card: CardItem) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteByName(name: String) {
        TODO("Not yet implemented")
    }
}