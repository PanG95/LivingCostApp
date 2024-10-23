package com.example.livingcostapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.livingcostapp.data.model.TransactionLiveCost
import com.example.livingcostapp.data.model.TransactionDao
import com.example.livingcostapp.data.model.TransactionType

class TransactionRepository(private val transactionDao: TransactionDao) {
    fun getTransactionsByType(type: TransactionType): LiveData<List<TransactionLiveCost>> {
        return transactionDao.getTransactionsByType(type)
    }

    suspend fun insert(transactionLiveCost: TransactionLiveCost) {
        transactionDao.insert(transactionLiveCost)
    }

    suspend fun delete(transactionLiveCost: TransactionLiveCost) {
        transactionDao.delete(transactionLiveCost)
    }
}