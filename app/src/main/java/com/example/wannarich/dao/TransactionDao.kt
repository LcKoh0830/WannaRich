package com.example.wannarich.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.wannarich.model.Transaction

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(trans: Transaction)

    @Query("SELECT SUM(amount) FROM `TRANSACTION` WHERE type == 1")
    fun getTotalIncome(): LiveData<Double>

    @Query("SELECT SUM(amount) FROM `TRANSACTION` WHERE type == 2")
    fun getTotalExpense(): LiveData<Double>

    @Query("SELECT * FROM `Transaction`")
    fun list(): LiveData<List<Transaction>>

    @Delete
    suspend fun deleteTransaction(trans: Transaction)

}