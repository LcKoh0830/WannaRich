package com.example.wannarich.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.wannarich.model.Transaction

@Dao
interface TransactionDao {

    @Insert
    suspend fun insertTransaction(trans: Transaction)

    @Query("SELECT SUM(amount) FROM `TRANSACTION` WHERE type == 1")
    fun getTotalIncome(): LiveData<Double>

    @Query("SELECT SUM(amount) FROM `TRANSACTION` WHERE type == 2")
    fun getTotalExpense(): LiveData<Double>

}