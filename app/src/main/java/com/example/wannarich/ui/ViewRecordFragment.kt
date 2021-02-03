package com.example.wannarich.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.wannarich.R
import com.example.wannarich.databinding.FragmentViewRecordBinding
import com.example.wannarich.model.CategoryExample
import com.example.wannarich.model.Transaction
import com.example.wannarich.utils.toString
import com.example.wannarich.viewmodel.TransactionViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ViewRecordFragment: Fragment(R.layout.fragment_view_record) {

    private lateinit var binding: FragmentViewRecordBinding
    private val args: ViewRecordFragmentArgs by navArgs()
    private lateinit var trans: Transaction
    private val viewModel: TransactionViewModel by viewModels()

    private val catExample = CategoryExample()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentViewRecordBinding.bind(view)

        setHasOptionsMenu(true)

        trans = args.trans

        with(binding) {
            when(trans.type) {
                1 -> {
                    ivRecordIcon.load(catExample.incomeList[trans.category - 1].icon)
                    tvCategory.text = catExample.incomeList[trans.category - 1].name
                    tvType.text = "Income"
                }
                2 -> {
                    ivRecordIcon.load(catExample.expenseList[trans.category - 1].icon)
                    tvCategory.text = catExample.expenseList[trans.category - 1].name
                    tvType.text = "Expense"
                }
                else -> {}
            }

            tvDate.text = trans.created_date.toString("dd/MM/yyyy hh:mm a")
            tvAmount.text = trans.amount.toString()
            tvRecordTitle.text = trans.description
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_view_record, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.view_record_edit -> {
                val action = ViewRecordFragmentDirections.actionViewRecordFragmentToAddTransactionFragment(trans)
                NavHostFragment.findNavController(this).navigate(action)
            }

            R.id.view_record_delete -> {
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage("Do you really want to delete this transaction?")
                    .setPositiveButton("DELETE") { _, _ ->
                        viewModel.deleteTransaction(trans)
                        NavHostFragment.findNavController(this).popBackStack()
                    }
                    .setNegativeButton("CANCEL") {dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}