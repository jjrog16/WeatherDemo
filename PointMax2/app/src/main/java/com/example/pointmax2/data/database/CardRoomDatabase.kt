package com.example.pointmax2.data.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pointmax2.data.database.entities.CardItem

// Annotates class to be a Room Database with a table (entity) of the Card class
@Database(entities = [CardItem::class], version = 1, exportSchema = false)
abstract class CardRoomDatabase : RoomDatabase() {

    abstract fun cardDao(): CardDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CardRoomDatabase? = null

        fun getDatabase(
                context: Context,
                scope: Application
        ): CardRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CardRoomDatabase::class.java,
                        "card_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}