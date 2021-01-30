package com.example.wannarich.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.wannarich.utils.Converters
import java.util.*

@Entity
data class Income(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "created_date") @TypeConverters(Converters::class) val created_date: Date
)

