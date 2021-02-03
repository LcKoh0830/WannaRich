package com.example.wannarich.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.example.wannarich.R
import com.example.wannarich.databinding.FragmentAddTransactionBinding
import com.example.wannarich.model.Category
import com.example.wannarich.model.CategoryExample
import com.example.wannarich.model.Transaction
import com.example.wannarich.utils.getCurrentDateTime
import com.example.wannarich.utils.hide
import com.example.wannarich.utils.show
import com.example.wannarich.utils.toString
import com.example.wannarich.viewmodel.TransactionViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTransactionFragment: Fragment(R.layout.fragment_add_transaction), CategoryPickerFragment.OnItemClick {

    private lateinit var binding: FragmentAddTransactionBinding
    private lateinit var actionBar: ActionBar
    private var bottomNav: BottomNavigationView? = null
    private val args: AddTransactionFragmentArgs by navArgs()
    private var trans: Transaction? = null

    private val viewModel: TransactionViewModel by viewModels()
    private var selectedCat: Category? = null

    private val catExample = CategoryExample()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAddTransactionBinding.bind(view)

        actionBar = (activity as AppCompatActivity).supportActionBar!!
        bottomNav = activity?.findViewById(R.id.bottom_navigation)
        bottomNav?.hide()

        trans = args.trans

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = binding.chipGroup.findViewById<Chip>(checkedId)
            if(chip != null) {
                actionBar.title = chip.text.toString()
            }
        }

        binding.tvCategory.setOnClickListener {
            val categoryFrag = CategoryPickerFragment(getSelectedChip(), this)
            categoryFrag.show(parentFragmentManager, categoryFrag.tag)
        }

        if(trans == null) { // new transaction
            actionBar.title = "Expense"
            binding.chipExpense.isChecked = true

            val date = getCurrentDateTime()
            binding.tvDate.text = date.toString("dd/MM/yyyy")
            binding.tvTime.text = date.toString("hh:mm a")

            binding.btnSave.setOnClickListener{
                val newTrans = Transaction(
                    type = getSelectedChip(),
                    amount = binding.etAmount.text.toString().toDouble(),
                    description = binding.etDescription.text.toString(),
                    category = selectedCat!!.index,
                    created_date = getCurrentDateTime()
                )
                viewModel.insertTransaction(newTrans)
                NavHostFragment.findNavController(this).popBackStack()
            }
        } else { // existing transaction
            trans?.let { trans ->
                when(trans.type) {
                    1 -> {
                        actionBar.title = "Income"
                        binding.chipIncome.isChecked = true
                        binding.tvCategory.text = catExample.incomeList[trans.category -1].name
                    }
                    2 -> {
                        actionBar.title = "Expense"
                        binding.chipExpense.isChecked = true
                        binding.tvCategory.text = catExample.expenseList[trans.category -1].name
                    }
                }

                binding.etAmount.setText(trans.amount.toString())
                binding.etDescription.setText(trans.description)
                binding.tvDate.text = trans.created_date.toString("dd/MM/yyyy")
                binding.tvTime.text = trans.created_date.toString("hh:mm a")

                binding.btnSave.setOnClickListener{
                    trans.apply {
                        type = getSelectedChip()
                        amount = binding.etAmount.text.toString().toDouble()
                        description = binding.etDescription.text.toString()
                        selectedCat?.let { category = it.index }
                        created_date = getCurrentDateTime()
                    }
                    viewModel.insertTransaction(trans)
                    NavHostFragment.findNavController(this).popBackStack()
                }
            }
        }
    }

    private fun getSelectedChip(): Int {
        val checkedId = binding.chipGroup.checkedChipId
        val chip = binding.chipGroup.findViewById<Chip>(checkedId)
        return when(chip.text) {
            "Income" -> 1
            "Expense" -> 2
            else -> 0
        }
    }

    override fun onSelectedCategory(cat: Category) {
        selectedCat = cat
        binding.tvCategory.text = cat.name
    }

    override fun onDestroy() {
        bottomNav?.show()
        super.onDestroy()
    }


}