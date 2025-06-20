package com.example.livingcostapp.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import com.example.livingcostapp.data.model.TransactionLiveCost
import com.example.livingcostapp.data.model.TransactionDao
import com.example.livingcostapp.data.model.TransactionType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import java.util.Date

open class TransactionRepository(private val transactionDao: TransactionDao) {
    open fun getTransactionsByType(type: TransactionType): LiveData<List<TransactionLiveCost>> {
        return transactionDao.getTransactionsByType(type)
    }

    fun getTransactionsByTypeInMonth(type: TransactionType, start: Date, end: Date): LiveData<List<TransactionLiveCost>> {
        return transactionDao.getTransactionsByTypeInMonth(type, start, end)
    }

    fun getTransactionsByCategoryInMonth(category: String, start: Date, end: Date): LiveData<List<TransactionLiveCost>> {
        return transactionDao.getTransactionsByCategoryInMonth(category, start, end)
    }


    open suspend fun insert(transactionLiveCost: TransactionLiveCost) {
        transactionDao.insert(transactionLiveCost)
    }

    open suspend fun delete(transactionLiveCost: TransactionLiveCost) {
        transactionDao.delete(transactionLiveCost)
    }

    fun getAllTransactions(): Flow<List<TransactionLiveCost>> {
        return transactionDao.getAllTransactions().asFlow()
    }
}