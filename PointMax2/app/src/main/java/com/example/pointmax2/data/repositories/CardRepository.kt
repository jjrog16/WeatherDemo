package com.example.pointmax2.data.repositories

import androidx.lifecycle.LiveData
import com.example.pointmax2.data.database.entities.CardItem

interface CardRepository {

    //Get a specific instance of a card
    fun getSpecificCard(name: String) : LiveData<CardItem>

    // Update or insert new card value
    suspend fun upsert(card: CardItem)

    // Delete a selected card
    suspend fun deleteByName(name: String)

}