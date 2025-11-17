package com.example.lab_week_10.database // Pastikan package sesuai

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity memberi tahu Room bahwa class ini adalah tabel
@Entity(tableName = "total")
data class Total(
    // @PrimaryKey menandakan ini adalah kunci unik untuk setiap baris
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    // @ColumnInfo untuk memberi nama kolom
    @ColumnInfo(name = "total")
    val total: Int = 0
)