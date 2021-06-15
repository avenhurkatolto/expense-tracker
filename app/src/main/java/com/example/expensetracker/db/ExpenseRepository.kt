package com.example.expensetracker.db

import androidx.annotation.WorkerThread
import com.example.expensetracker.model.ExpenseModel
import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val expenseDao: ExpenseDao) {
    val allExpenses: Flow<List<ExpenseModel>> = expenseDao.getExpenses()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    suspend fun insert(expenseModel: ExpenseModel) {
        expenseDao.insert(expenseModel)
    }
}