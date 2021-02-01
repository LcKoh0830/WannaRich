package com.example.wannarich.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.wannarich.databinding.ItemCategoryPickerBinding
import com.example.wannarich.model.Category

class CategoryAdapter(
    private var categoryList: List<Category>,
    private val listener: OnItemClick
): RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {

    interface OnItemClick{
        fun onSelectedCat(selected: Category)
    }

    internal fun setList(catList: List<Category>) {
        categoryList = catList
        notifyDataSetChanged()
    }

    inner class CategoryVH(val binding: ItemCategoryPickerBinding):  RecyclerView.ViewHolder(binding.root) {
        fun bind(cat: Category) {
            with(binding) {
                ivCategory.load(cat.icon)
                tvCategory.text = cat.name

                rlCategoryItem.setOnClickListener {
                    listener.onSelectedCat(cat)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val binding = ItemCategoryPickerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryVH(binding)
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}