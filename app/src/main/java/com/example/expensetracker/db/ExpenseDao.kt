package com.example.expensetracker.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.expensetracker.model.ExpenseModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expense_table")
    fun getExpenses(): Flow<List<ExpenseModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(expense: ExpenseModel)

    @Query("DELETE FROM expense_table")
    suspend fun deleteAll()
}