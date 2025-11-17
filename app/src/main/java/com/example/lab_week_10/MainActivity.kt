package com.example.lab_week_10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_10.viewmodels.TotalViewModel // Import ViewModel Anda

class MainActivity : AppCompatActivity() {

    // Inisialisasi ViewModel menggunakan 'by lazy'
    // 'by lazy' artinya ViewModel baru akan dibuat saat pertama kali diakses.
    // ViewModelProvider akan mengambil ViewModel yang sudah ada jika terjadi rotasi,
    // atau membuat yang baru jika belum ada.
    private val viewModel: TotalViewModel by lazy {
        ViewModelProvider(this)[TotalViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Panggil fungsi untuk menyiapkan ViewModel
        prepareViewModel()
    }

    // Fungsi ini akan mengupdate TextView
    private fun updateText(total: Int) {
        findViewById<TextView>(R.id.text_total).text =
            getString(R.string.text_total, total)
    }

    // Fungsi ini menyiapkan 'observer' dan 'click listener'
    private fun prepareViewModel() {

        // 1. Mengamati (observe) LiveData
        // Ini adalah inti dari LiveData. Blok kode di dalam { ... }
        // akan otomatis berjalan SETIAP KALI data 'viewModel.total' berubah.
        viewModel.total.observe(this) { total ->
            // Saat data berubah, panggil updateText()
            updateText(total)
        }

        // 2. Menyiapkan tombol
        // Saat tombol diklik, kita panggil fungsi 'incrementTotal()' di ViewModel.
        // ViewModel akan mengubah data -> LiveData akan ter-update ->
        // Observer di atas akan berjalan -> UI akan ter-update.
        findViewById<Button>(R.id.button_increment).setOnClickListener {
            viewModel.incrementTotal()
        }
    }
}