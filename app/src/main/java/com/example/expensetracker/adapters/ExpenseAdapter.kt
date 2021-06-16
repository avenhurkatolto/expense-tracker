package com.example.expensetracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.R
import com.example.expensetracker.model.ExpenseModel
import java.text.SimpleDateFormat
import java.util.*

class ExpenseAdapter : ListAdapter<ExpenseModel, ExpenseAdapter.ExpenseViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        return ExpenseViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.title, current.expense, current.date)
    }

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText: TextView = itemView.findViewById(R.id.title)
        private val dateText: TextView = itemView.findViewById(R.id.date)
        private val expenseText: TextView = itemView.findViewById(R.id.expense)

        fun bind(title: String?, expense: Int, date: Date) {
            titleText.text = title
            dateText.text = formatter.format(date)
            expenseText.text = expense.toString() + " Ft"
        }

        companion object {
            fun create(parent: ViewGroup): ExpenseViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_expense_list, parent, false)
                return ExpenseViewHolder(view)
            }
        }
    }

    companion object {
        val formatter = SimpleDateFormat("yyyy.MM.dd.")

        private val COMPARATOR = object : DiffUtil.ItemCallback<ExpenseModel>() {
            override fun areItemsTheSame(oldItem: ExpenseModel, newItem: ExpenseModel): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: ExpenseModel, newItem: ExpenseModel): Boolean {
                return oldItem.date == newItem.date
            }
        }
    }
}
