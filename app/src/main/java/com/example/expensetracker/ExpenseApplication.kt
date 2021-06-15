package com.example.expensetracker

import android.app.Application
import com.example.expensetracker.db.ExpenseDatabase
import com.example.expensetracker.db.ExpenseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ExpenseApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())


    val database by lazy { ExpenseDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ExpenseRepository(database.expenseDao()) }
}
