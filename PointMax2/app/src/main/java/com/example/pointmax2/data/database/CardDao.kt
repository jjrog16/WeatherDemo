package com.example.pointmax2.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pointmax2.data.database.entities.CardItem

@Dao
interface CardDao {
    @Query("SELECT * from CardItem")
    fun getCards(): LiveData<List<CardItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: CardItem)

    @Query("DELETE FROM CardItem")
    suspend fun deleteAll()

    @Query("DELETE FROM CardItem WHERE cardName = :name")
    suspend fun deleteByName(name: String)
}