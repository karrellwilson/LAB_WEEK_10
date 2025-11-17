package com.example.lab_week_10.database // Pastikan package sesuai

import androidx.room.Database
import androidx.room.RoomDatabase

// @Database mendefinisikan semua entity (tabel) dan versi database
@Database(entities = [Total::class], version = 1)
abstract class TotalDatabase : RoomDatabase() {

    // Deklarasikan DAO agar bisa diakses
    abstract fun totalDao(): TotalDao
}