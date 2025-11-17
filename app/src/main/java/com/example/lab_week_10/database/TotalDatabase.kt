package com.example.lab_week_10.database

import androidx.room.Database
import androidx.room.RoomDatabase

// UBAH VERSI DI SINI
@Database(entities = [Total::class], version = 2)
abstract class TotalDatabase : RoomDatabase() {

    abstract fun totalDao(): TotalDao
}