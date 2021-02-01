package com.example.wannarich.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wannarich.adapters.CategoryAdapter
import com.example.wannarich.databinding.FragmentCategoryPickerBinding
import com.example.wannarich.model.Category
import com.example.wannarich.model.CategoryExample
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CategoryPickerFragment(
    val type: Int,
    val listener: OnItemClick
) : DialogFragment(), CategoryAdapter.OnItemClick {

    private lateinit var binding: FragmentCategoryPickerBinding
    private lateinit var adapter: CategoryAdapter
    private val catList = listOf<Category>()

    interface OnItemClick {
        fun onSelectedCategory(cat: Category)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryPickerBinding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.rvCategory.layoutManager = GridLayoutManager(context, 2)
        adapter = CategoryAdapter(catList, this)
        when (type) {
            1 -> { // INCOME
                adapter.setList(CategoryExample().incomeList)
            }
            2 -> { // EXPENSE
                adapter.setList(CategoryExample().expenseList)
            }
        }
        binding.rvCategory.adapter = adapter
    }

    override fun onSelectedCat(selected: Category) {
        listener.onSelectedCategory(selected)
        dismiss()
    }
}