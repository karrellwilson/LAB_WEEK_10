package com.example.lab_week_10.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TotalViewModel : ViewModel() {

    // 1. _total (MutableLiveData): Ini adalah data internal yang BISA diubah.
    //    Kita buat 'private' agar hanya ViewModel ini yang bisa mengubah nilainya.
    private val _total = MutableLiveData<Int>()

    // 2. total (LiveData): Ini adalah data publik yang HANYA BISA dibaca/diamati.
    //    Activity akan mengamati (observe) variabel ini.
    val total: LiveData<Int>
        get() = _total

    // 3. init: Blok ini akan berjalan saat ViewModel pertama kali dibuat.
    //    Kita atur nilai awalnya ke 0.
    init {
        _total.value = 0
    }

    // 4. incrementTotal: Fungsi ini akan dipanggil oleh tombol.
    //    Ia mengambil nilai saat ini, menambahkannya, dan mengatur ulang nilainya.
    fun incrementTotal() {
        // Kita gunakan .value untuk mendapatkan atau mengatur data di dalam LiveData
        // (?: 0) artinya "jika nilainya null, gunakan 0"
        _total.value = (_total.value ?: 0) + 1
    }
}