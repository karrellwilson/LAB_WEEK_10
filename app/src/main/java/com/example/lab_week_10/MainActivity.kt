package com.example.lab_week_10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_10.viewmodels.TotalViewModel // Import ViewModel Anda

class MainActivity : AppCompatActivity() {

    // Inisialisasi ViewModel menggunakan 'by lazy'
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
        viewModel.total.observe(this) { total ->
            // Saat data berubah, panggil updateText()
            updateText(total)
        }

        // 2. Menyiapkan tombol
        findViewById<Button>(R.id.button_increment).setOnClickListener {
            viewModel.incrementTotal()
        }
    }
}