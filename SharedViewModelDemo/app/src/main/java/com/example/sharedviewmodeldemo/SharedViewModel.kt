package com.example.sharedviewmodeldemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    // Internally, we use a MutableLiveData to handle navigation to the selected cxa
    private var _textToSend = MutableLiveData<String>()

    // The external immutable LiveData for the navigation property
    val textToSend: LiveData<String>
        get() = _textToSend

    fun setText(input : String){
        _textToSend.value = input
    }

    var isSent: Boolean = false
}