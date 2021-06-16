package com.example.expensetracker.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.ExpenseApplication
import com.example.expensetracker.R
import com.example.expensetracker.adapters.ExpenseAdapter
import com.example.expensetracker.databinding.FragmentMainBinding
import com.example.expensetracker.viewmodel.ExpenseViewModel
import com.example.expensetracker.viewmodel.ExpenseViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null

    private val viewModel: ExpenseViewModel by activityViewModels() {
        ExpenseViewModelFactory((activity?.application as ExpenseApplication).repository)
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerview
        val adapter = ExpenseAdapter()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        viewModel.allExpenses.observe(viewLifecycleOwner) { list ->
            list.let { adapter.submitList(it) }

            var sum : Int = 0
            list.forEach{
                sum+=it.expense
            }
           binding.sum.text = sum.toString() + "Ft"
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}