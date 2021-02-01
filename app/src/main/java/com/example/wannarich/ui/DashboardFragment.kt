package com.example.wannarich.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.wannarich.R
import com.example.wannarich.databinding.FragmentDashboardBinding
import com.example.wannarich.viewmodel.TransactionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private lateinit var binding: FragmentDashboardBinding
    private val viewModel: TransactionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDashboardBinding.bind(view)

        binding.fabAddTransaction.setOnClickListener {
            val action = DashboardFragmentDirections.actionNavigationDashboardToAddTransactionFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }

        viewModel.totalIncome.observe(viewLifecycleOwner, {

            binding.tvTotalIncome.text = it?.toString() ?: "0"
        })

        viewModel.totalExpense.observe(viewLifecycleOwner, {
            binding.tvTotalExpense.text = it?.toString() ?: "0"
        })

        viewModel.totalBalance.observe(viewLifecycleOwner, {
            binding.tvTotalBalance.text = it?.toString() ?: "0"
        })
    }
}