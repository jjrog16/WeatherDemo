package com.example.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.data.models.Daily

@Dao
interface OpenWeatherDao {

    // Returns ID that was inserted
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(daily: Daily): Int

    @Query("SELECT * FROM Daily")
    fun getAllDaily(): LiveData<List<Daily>>

    @Delete
    suspend fun deleteDaily(daily: Daily)
}