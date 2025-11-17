package com.example.lab_week_10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room // BARU: Import Room
import com.example.lab_week_10.database.Total // BARU: Import Entity Total
import com.example.lab_week_10.database.TotalDatabase // BARU: Import Database
import com.example.lab_week_10.viewmodels.TotalViewModel

class MainActivity : AppCompatActivity() {

    // BARU: Buat ID unik untuk data kita (Langkah 13)
    // Kita hanya akan punya 1 baris di tabel, dengan ID = 1
    companion object {
        const val ID: Long = 1
    }

    // BARU: Inisialisasi Database 'by lazy' (Langkah 11)
    private val db by lazy {
        prepareDatabase()
    }

    private val viewModel: TotalViewModel by lazy {
        ViewModelProvider(this)[TotalViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // BARU: Ambil nilai awal dari database (Langkah 12)
        initializeValueFromDatabase()

        // Siapkan ViewModel (ini sudah ada sebelumnya)
        prepareViewModel()
    }

    // BARU: Fungsi untuk menyimpan data ke DB saat app di-pause (Langkah 15)
    override fun onPause() {
        super.onPause()
        // Ambil nilai terakhir dari ViewModel dan update database
        // Kita gunakan '!!' (non-null assertion) sesuai modul
        db.totalDao().update(Total(ID, viewModel.total.value!!))
    }

    private fun updateText(total: Int) {
        findViewById<TextView>(R.id.text_total).text =
            getString(R.string.text_total, total)
    }

    private fun prepareViewModel() {
        viewModel.total.observe(this) { total ->
            updateText(total)
        }

        findViewById<Button>(R.id.button_increment).setOnClickListener {
            viewModel.incrementTotal()
        }
    }

    // BARU: Fungsi untuk membangun instance database (Langkah 13)
    private fun prepareDatabase(): TotalDatabase {
        return Room.databaseBuilder(
            applicationContext,
            TotalDatabase::class.java,
            "total-database" // Nama file database
        )
            // .allowMainThreadQueries() HANYA untuk simplisitas di modul ini
            // Idealnya, operasi database harus di background thread
            .allowMainThreadQueries()
            .build()
    }

    // BARU: Fungsi untuk mengambil data dari DB (Langkah 13)
    private fun initializeValueFromDatabase() {
        // Ambil data dengan ID = 1
        val total = db.totalDao().getTotal(ID)

        if (total.isEmpty()) {
            // Jika database kosong (pertama kali app dibuka),
            // masukkan data baru dengan total = 0
            db.totalDao().insert(Total(id = ID, total = 0))
        } else {
            // Jika data ada, setel nilai ViewModel
            // dengan data dari database
            viewModel.setTotal(total.first().total)
        }
    }
}