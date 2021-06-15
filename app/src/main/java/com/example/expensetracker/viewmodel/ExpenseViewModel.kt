package com.example.expensetracker.viewmodel

import androidx.lifecycle.*
import com.example.expensetracker.db.ExpenseRepository
import com.example.expensetracker.model.ExpenseModel
import kotlinx.coroutines.launch

class ExpenseViewModel(private val repository: ExpenseRepository) : ViewModel() {


    val allExpenses: LiveData<List<ExpenseModel>> = repository.allExpenses.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(expenseModel: ExpenseModel) = viewModelScope.launch {
        repository.insert(expenseModel)
    }
}

class ExpenseViewModelFactory(private val repository: ExpenseRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpenseViewModel::class.java)) {
            return ExpenseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}