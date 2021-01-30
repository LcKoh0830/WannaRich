package com.example.wannarich.db


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wannarich.dao.ExpenseDao
import com.example.wannarich.dao.IncomeDao
import com.example.wannarich.model.Expense
import com.example.wannarich.model.Income
import com.example.wannarich.utils.Converters

@Database(
    version = 1,
    entities = [
        Income::class,
        Expense::class
    ]
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun incomeDao(): IncomeDao
    abstract fun expenseDao(): ExpenseDao
}