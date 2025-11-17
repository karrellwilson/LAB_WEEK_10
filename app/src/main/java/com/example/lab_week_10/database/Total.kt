package com.example.lab_week_10.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

// Ini adalah class data baru untuk "ditanamkan"
data class TotalObject(
    @ColumnInfo(name = "value") val value: Int,
    @ColumnInfo(name = "date") val date: String
)

// Perhatikan perubahannya di @Entity
@Entity(tableName = "total")
data class Total(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    // @Embedded memberi tahu Room untuk memperlakukan
    // field 'value' dan 'date' dari TotalObject
    // seolah-olah mereka adalah kolom di tabel 'total'
    @Embedded val total: TotalObject
)