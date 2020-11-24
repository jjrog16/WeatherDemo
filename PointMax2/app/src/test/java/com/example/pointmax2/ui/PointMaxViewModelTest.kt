package com.example.pointmax2.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.getOrAwaitValue
import com.example.pointmax2.data.database.entities.CardItem
import com.example.pointmax2.data.repositories.CardRepository
import com.example.pointmax2.data.repositories.FakeCardRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PointMaxViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: PointMaxViewModel

    @Before
    fun setUp() {
        viewModel = PointMaxViewModel(FakeCardRepository())

    }

    /**
     * Naming scheme as follows: subjectUnderTest_actionOrInput_resultState()
     */

}

