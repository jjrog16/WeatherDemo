package com.example.pointmax2.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pointmax2.data.database.entities.CardEntity

@Dao
interface CardDao {
    @Query("SELECT * from CardEntity")
    fun getCards(): LiveData<List<CardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: CardEntity)

    @Query("DELETE FROM CardEntity")
    suspend fun deleteAll()

    @Query("DELETE FROM CardEntity WHERE cardName = :name")
    suspend fun deleteByName(name: String)
}