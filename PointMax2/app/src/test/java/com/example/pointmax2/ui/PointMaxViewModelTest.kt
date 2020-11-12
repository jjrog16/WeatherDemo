package com.example.pointmax2.ui

import com.example.getOrAwaitValue
import com.example.pointmax2.data.database.entities.CardItem
import com.example.pointmax2.data.repositories.CardRepository
import com.example.pointmax2.data.repositories.FakeCardRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PointMaxViewModelTest {

    private lateinit var viewModel: PointMaxViewModel

    @Before
    fun setUp() {
        viewModel = PointMaxViewModel(FakeCardRepository())

    }

    /**
     * Naming scheme as follows: subjectUnderTest_actionOrInput_resultState()
     */

    @Test
    fun upsert_insertACard_returnsIfCardExists() = runBlockingTest {
        val testCard = CardItem(cardName = "Amex Platinum", airlines = 5.0)
        viewModel.upsert(testCard)

        // getOrAwaitValue is located in the LiveDataUtilAndroidTest file provided by Google
        // and allows for the observation of LiveData in testing where you cannot use asynchronous
        // code to run in threads
        val observeAllCards = viewModel.observeAllCards().getOrAwaitValue()

        Truth.assertThat(observeAllCards).contains(testCard)
    }

    @Test
    fun upsert_insertDuplicateNamesWithUniqueValues_returnsOnlyLastCardInserted() = runBlockingTest {
        val cardsToInsert = arrayOf(
            CardItem(id = 1, cardName = "Card Start"),
            CardItem(id = 1, cardName = "Card End", restaurants = 2.0)
        )

        // Insert each card into the database
        cardsToInsert.forEach {
            viewModel.upsert(it)
        }

        val observeAllCards = viewModel.observeAllCards().getOrAwaitValue()
        Truth.assertThat(observeAllCards).doesNotContain(CardItem(cardName = "Card Start"))
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
            viewModel.upsert(it)
        }

        // Clear all cards in the database
        viewModel.deleteAll()

        // Observe the database
        val observeAllCards = viewModel.observeAllCards().getOrAwaitValue()

        Truth.assertThat(observeAllCards).isEmpty()
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
            viewModel.upsert(it)
        }

        viewModel.deleteByName("Card 2")

        val observeAllCards = viewModel.observeAllCards().getOrAwaitValue()

        Truth.assertThat(observeAllCards).doesNotContain(CardItem(cardName = "Card 2"))
    }

    @Test
    fun getSpecificCard_findInsertedCard() = runBlockingTest{
        val cardsToInsert = arrayOf(
            CardItem(cardName = "Card 1"),
            CardItem(cardName = "Card 2"),
            CardItem(cardName = "Card 3")
        )

        // Insert each card into the database
        cardsToInsert.forEach {
            viewModel.upsert(it)
        }

        val specificCard = viewModel.getSpecificCard("Card 1").getOrAwaitValue()

        Truth.assertThat(specificCard).isEqualTo(CardItem(cardName = "Card 1"))
    }
}

