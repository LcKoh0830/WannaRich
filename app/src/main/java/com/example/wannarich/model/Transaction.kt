package com.example.wannarich.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.wannarich.utils.Converters
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "type") var type: Int,  // 1-income, 2-expense
    @ColumnInfo(name = "amount") var amount: Double,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "category") var category: Int,
    @ColumnInfo(name = "created_date") @TypeConverters(Converters::class) var created_date: Date,
): Parcelable

