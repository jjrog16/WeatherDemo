package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.models.Daily

@Database(
    entities = [Daily::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class OpenWeatherDatabase : RoomDatabase() {

    abstract fun dailyDao(): OpenWeatherDao

    companion object {
        @Volatile
        private var instance: OpenWeatherDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                OpenWeatherDatabase::class.java,
                "daily_db.db"
            ).build()
    }
}