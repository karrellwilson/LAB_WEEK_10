package com.example.lab_week_10.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TotalViewModel : ViewModel() {

    private val _total = MutableLiveData<Int>()
    val total: LiveData<Int>
        get() = _total

    init {
        // postValue digunakan untuk update dari background thread.
        // .value digunakan untuk update dari main thread.
        // Di sini, keduanya berfungsi, tapi kita ikuti modul.
        _total.postValue(0)
    }

    fun incrementTotal() {
        // Gunakan postValue()
        _total.postValue((_total.value ?: 0) + 1)
    }
}