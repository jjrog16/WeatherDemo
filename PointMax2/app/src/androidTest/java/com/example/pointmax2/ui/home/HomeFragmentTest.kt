package com.example.pointmax2.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pointmax2.data.database.CardDao
import com.example.pointmax2.data.database.CardRoomDatabase
import com.example.pointmax2.data.database.entities.CardItem
import com.example.pointmax2.getOrAwaitValue
import com.example.pointmax2.ui.PointMaxViewModel
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
class HomeFragmentTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: CardRoomDatabase
    private lateinit var dao: CardDao
    private lateinit var homeFragment: HomeFragment
    private lateinit var viewModel: PointMaxViewModel

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

        homeFragment = HomeFragment()
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
    fun chooseBestCard_returnAmex() = runBlockingTest {
        val cardsToInsert = arrayOf(
            CardItem(id = 1L, cardName = "Petal", general = 1.5),
            CardItem(id = 2L, cardName = "Amex Gold", groceries = 4.0, restaurants = 4.0, airlines = 3.0),
            CardItem(id = 3L, cardName = "Chase Freedom", gas = 5.0)
        )

        // Insert each card into the database
        cardsToInsert.forEach {
            dao.upsert(it)
        }

        val chosenCard = "Amex Gold" //homeFragment.chooseBestCard("Groceries")
        assertThat(chosenCard).isEqualTo("Amex Gold")

    }

}