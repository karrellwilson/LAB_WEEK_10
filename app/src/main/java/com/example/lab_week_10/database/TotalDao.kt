package com.example.lab_week_10.database // Pastikan package sesuai

import androidx.room.*

@Dao
interface TotalDao {
    // (Create) Menyisipkan data baru, jika ada ganti (REPLACE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(total: Total)

    // (Update) Memperbarui data yang ada
    @Update
    fun update(total: Total)

    // (Delete) Menghapus data
    @Delete
    fun delete(total: Total)

    // (Read) Mengambil data berdasarkan ID
    @Query("SELECT * FROM total WHERE id = :id")
    fun getTotal(id: Long): List<Total> // Menggunakan List agar bisa dicek 'isEmpty()'
}