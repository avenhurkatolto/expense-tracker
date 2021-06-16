package com.example.expensetracker.db

import androidx.annotation.WorkerThread
import com.example.expensetracker.model.ExpenseModel
import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val expenseDao: ExpenseDao) {
    val allExpenses: Flow<List<ExpenseModel>> = expenseDao.getExpenses()

    @WorkerThread
    suspend fun insert(expenseModel: ExpenseModel) {
        expenseDao.insert(expenseModel)
    }
}