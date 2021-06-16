package com.example.expensetracker.view

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentCreateExpenseBinding
import com.example.expensetracker.model.ExpenseModel
import com.example.expensetracker.viewmodel.ExpenseViewModel
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CreateExpenseFragment : Fragment() {

    private val viewModel: ExpenseViewModel by activityViewModels()

    private var _binding: FragmentCreateExpenseBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCreateExpenseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSave.setOnClickListener {
            if (TextUtils.isEmpty(binding.editExpense.text) || TextUtils.isEmpty(binding.editTitle.text)) {
                Toast.makeText(activity, "Please fill all fields!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.insert(
                    ExpenseModel(
                        binding.editTitle.text.toString(),
                        binding.editExpense.text.toString().toInt(),
                        Calendar.getInstance().time
                    )
                )
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}