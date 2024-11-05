package com.example.livingcostapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.livingcostapp.data.model.TransactionLiveCost
import com.example.livingcostapp.data.model.TransactionType

interface TransactionRepositoryInterface {
    fun getTransactionsByType(type: TransactionType): LiveData<List<TransactionLiveCost>>
    suspend fun insert(transactionLiveCost: TransactionLiveCost)
    suspend fun delete(transactionLiveCost: TransactionLiveCost)
}