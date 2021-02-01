package com.example.wannarich.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.wannarich.R
import com.example.wannarich.adapters.TransactionAdapter
import com.example.wannarich.databinding.FragmentDashboardBinding
import com.example.wannarich.model.Transaction
import com.example.wannarich.viewmodel.TransactionViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard), TransactionAdapter.OnItemClick {

    private lateinit var binding: FragmentDashboardBinding
    private val viewModel: TransactionViewModel by viewModels()
    private lateinit var adapter: TransactionAdapter
    private val transList = listOf<Transaction>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDashboardBinding.bind(view)

        setupRecyclerView()

        binding.fabAddTransaction.setOnClickListener {
            val action = DashboardFragmentDirections.actionNavigationDashboardToAddTransactionFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }

        viewModel.totalIncome.observe(viewLifecycleOwner, {
            binding.cardviewDashboard.tvTotalIncome.text = it?.toString() ?: "0"
        })

        viewModel.totalExpense.observe(viewLifecycleOwner, {
            binding.cardviewDashboard.tvTotalExpense.text = it?.toString() ?: "0"
        })

        viewModel.totalBalance.observe(viewLifecycleOwner, {
            binding.cardviewDashboard.tvTotalBalance.text = it?.toString() ?: "0"
        })

        viewModel.allTransaction.observe(viewLifecycleOwner, {
            adapter.setTransactionList(it)
        })
    }

    private fun setupRecyclerView() {
        binding.rvTransList.layoutManager = LinearLayoutManager(context)
        adapter = TransactionAdapter(transList, this)
        binding.rvTransList.adapter = adapter
    }

    override fun onTransactionClick(trans: Transaction) {
        // TODO - transaction preview screen (edit/delete)
        Timber.i("$trans")
    }
}