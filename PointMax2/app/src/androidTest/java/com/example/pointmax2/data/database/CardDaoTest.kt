package com.example.android.pointmax.data.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pointmax2.data.database.CardDao
import com.example.pointmax2.data.database.CardRoomDatabase
import com.example.pointmax2.data.database.entities.CardItem
import com.example.pointmax2.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CardDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    
    private lateinit var database: CardRoomDatabase
    private lateinit var dao: CardDao
    
    /**
     * Before each test, initialize a new Room database, store it in RAM memory, and initialize
     * the DAO
     */
    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CardRoomDatabase::class.java
        ).allowMainThreadQueries().build()
        
        dao = database.cardDao()
    }
    
    /**
     * Close the database after each test
     */
    @After
    fun teardown(){
        database.close()
    }
    
    /**
     * Naming scheme as follows: subjectUnderTest_actionOrInput_resultState()
     */
     
    @Test
    fun insert_insertACard_returnsIfCardExists() = runBlockingTest {
        val testCard = CardItem(cardName = "Amex Platinum", airlines = 5.0)
        dao.insert(testCard)
        
        // getOrAwaitValue is located in the LiveDataUtilAndroidTest file provided by Google
        // and allows for the observation of LiveData in testing where you cannot use asynchronous
        // code to run in threads
        val observeAllCards = dao.getCards().getOrAwaitValue()
        
        assertThat(observeAllCards).contains(testCard)
    }
    
    @Test
    fun insert_insertDuplicateNamesWithUniqueValues_returnsOnlyLastCardInserted() = runBlockingTest {
        val cardsToInsert = arrayOf(
            CardItem(id = 1, cardName = "Card 1"),
            CardItem(id = 1, cardName = "Card 1", restaurants = 2.0),
        )
    
        // Insert each card into the database
        cardsToInsert.forEach {
            dao.insert(it)
        }
        
        val observeAllCards = dao.getCards().getOrAwaitValue()
        assertThat(observeAllCards).doesNotContain(CardItem(cardName = "Card 1"))
    }
    
    @Test
    fun deleteAll_deleteAllInsertedCards_returnsIfEmpty() = runBlockingTest {
        val cardsToInsert = arrayOf(
            CardItem(cardName = "Card 1"),
            CardItem(cardName = "Card 2"),
            CardItem(cardName = "Card 3")
        )
        
        // Insert each card into the database
        cardsToInsert.forEach {
            dao.insert(it)
        }
        
        // Clear all cards in the database
        dao.deleteAll()
        
        // Observe the database
        val observeAllCards = dao.getCards().getOrAwaitValue()
        
        assertThat(observeAllCards).isEmpty()
    }
    
    @Test
    fun deleteByName_deleteSelectedCard_returnsCardIsNotInDatabase() = runBlockingTest{
        val cardsToInsert = arrayOf(
            CardItem(cardName = "Card 1"),
            CardItem(cardName = "Card 2"),
            CardItem(cardName = "Card 3")
        )
    
        // Insert each card into the database
        cardsToInsert.forEach {
            dao.insert(it)
        }
        
        dao.deleteByName("Card 2")
        
        val observeAllCards = dao.getCards().getOrAwaitValue()
        
        assertThat(observeAllCards).doesNotContain(CardItem(cardName = "Card 2"))
    }
    
}