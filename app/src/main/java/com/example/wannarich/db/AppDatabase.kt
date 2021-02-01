package com.example.wannarich.db


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wannarich.dao.TransactionDao
import com.example.wannarich.model.Transaction
import com.example.wannarich.utils.Converters

@Database(
    version = 1,
    entities = [
        Transaction::class
    ]
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}