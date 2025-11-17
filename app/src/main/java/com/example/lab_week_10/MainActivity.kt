package com.example.lab_week_10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast // BARU: Import Toast
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.lab_week_10.database.Total
import com.example.lab_week_10.database.TotalDatabase
import com.example.lab_week_10.database.TotalObject // BARU: Import TotalObject
import com.example.lab_week_10.viewmodels.TotalViewModel
import java.util.Date // BARU: Import Date untuk timestamp

class MainActivity : AppCompatActivity() {

    companion object {
        const val ID: Long = 1
    }

    private val db by lazy {
        prepareDatabase()
    }

    private val viewModel: TotalViewModel by lazy {
        ViewModelProvider(this)[TotalViewModel::class.java]
    }

    // BARU: Tambahkan variabel untuk menyimpan tanggal terakhir
    private var lastUpdateDate: String = "Never"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeValueFromDatabase()
        prepareViewModel()
    }

    // BARU: Tampilkan Toast saat Activity dimulai (Langkah Bonus)
    override fun onStart() {
        super.onStart()
        Toast.makeText(this, lastUpdateDate, Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()

        // UPDATE: Buat TotalObject baru dengan nilai & tanggal saat ini
        db.totalDao().update(
            Total(
                ID,
                TotalObject(
                    value = viewModel.total.value!!,
                    date = Date().toString() // Dapatkan tanggal & waktu saat ini
                )
            )
        )
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

    private fun prepareDatabase(): TotalDatabase {
        return Room.databaseBuilder(
            applicationContext,
            TotalDatabase::class.java,
            "total-database"
        )
            .allowMainThreadQueries()
            // BARU: Tambahkan ini
            // Ini akan menghapus DB lama (v1) dan membuat baru (v2)
            // Ini adalah cara mudah untuk menangani migrasi di lab
            .fallbackToDestructiveMigration()
            .build()
    }

    private fun initializeValueFromDatabase() {
        val total = db.totalDao().getTotal(ID)

        if (total.isEmpty()) {
            // UPDATE: Masukkan TotalObject baru saat pertama kali
            val initialDate = Date().toString()
            db.totalDao().insert(
                Total(
                    id = ID,
                    total = TotalObject(value = 0, date = initialDate)
                )
            )
            lastUpdateDate = initialDate
        } else {
            // UPDATE: Ambil nilai dari .total.value dan tanggal dari .total.date
            viewModel.setTotal(total.first().total.value)
            lastUpdateDate = total.first().total.date
        }
    }
}