package com.example.livingcostapp.mock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.livingcostapp.data.model.TransactionLiveCost
import com.example.livingcostapp.data.model.TransactionType
import com.example.livingcostapp.domain.repository.TransactionRepositoryInterface

class MockTransactionRepository : TransactionRepositoryInterface {
    override fun getTransactionsByType(type: TransactionType): LiveData<List<TransactionLiveCost>> {
        val sampleTransactions = listOf(
            TransactionLiveCost(id = 1,  amount = 100.0, type = TransactionType.INCOME),
            TransactionLiveCost(id = 2, amount = 150.0, type = TransactionType.INCOME)
        )
        return MutableLiveData(sampleTransactions)
    }

    override suspend fun insert(transactionLiveCost: TransactionLiveCost) {
        // Mock, nie robi nic
    }

    override suspend fun delete(transactionLiveCost: TransactionLiveCost) {
        // Mock, nie robi nic
    }
}