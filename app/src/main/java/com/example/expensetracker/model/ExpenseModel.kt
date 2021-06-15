package com.example.expensetracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "expense_table")
class ExpenseModel(
    val title: String,
    val expense: Int,
    @PrimaryKey val date: Date
)