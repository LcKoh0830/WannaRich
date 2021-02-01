package com.example.wannarich.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wannarich.dao.TransactionDao
import com.example.wannarich.model.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.exp


class TransactionViewModel @ViewModelInject constructor(
    private val transactionDao: TransactionDao
):ViewModel() {

    val totalIncome = transactionDao.getTotalIncome()
    val totalExpense = transactionDao.getTotalExpense()

    val totalBalance = MediatorLiveData<Double?>()

    val allTransaction = transactionDao.list()

    init {
        // observe any changes of income/expense to trigger changes in balance
        totalBalance.addSource(totalIncome) {
            totalBalance.value = getDifference(totalIncome.value, totalExpense.value)
        }
        totalBalance.addSource(totalExpense) {
            totalBalance.value = getDifference(totalIncome.value, totalExpense.value)
        }
    }

    fun insertTransaction(trans: Transaction) {
        viewModelScope.launch {
            transactionDao.insertTransaction(trans)
        }
    }



    private fun getDifference(income: Double?, expense: Double?): Double?{
        return if(income != null && expense != null) {
            income - expense
        } else null
    }





}