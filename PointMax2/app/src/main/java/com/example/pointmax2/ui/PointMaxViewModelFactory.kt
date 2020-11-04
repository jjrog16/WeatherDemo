package com.example.pointmax2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pointmax2.data.repositories.CardRepository

/**
 * Simple ViewModel factory that provides the repository to the ViewModel.
 */
class PointMaxViewModelFactory(
        private val repository: CardRepository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PointMaxViewModel::class.java)) {
            return PointMaxViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}