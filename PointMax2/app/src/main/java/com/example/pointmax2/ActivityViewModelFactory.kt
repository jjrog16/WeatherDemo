package com.example.pointmax2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pointmax2.data.repositories.CardRepository

/**
 * Simple ViewModel factory that provides the card name and context to the ViewModel.
 */
class ActivityViewModelFactory(
        private val repository: CardRepository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActivityViewModel::class.java)) {
            return ActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}