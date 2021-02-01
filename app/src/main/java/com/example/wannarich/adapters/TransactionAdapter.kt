package com.example.wannarich.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.wannarich.databinding.ItemTransactionBinding
import com.example.wannarich.model.CategoryExample
import com.example.wannarich.model.Transaction
import com.example.wannarich.utils.toString

class TransactionAdapter(
    private var transList: List<Transaction>,
    private val listener: OnItemClick
): RecyclerView.Adapter<TransactionAdapter.TransactionVH>() {

    interface OnItemClick {
        fun onTransactionClick(trans: Transaction)
    }

    private val catExample = CategoryExample()

    internal fun setTransactionList(transactionList: List<Transaction>) {
        transList = transactionList
        notifyDataSetChanged()
    }

    inner class TransactionVH(val binding: ItemTransactionBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(trans: Transaction) {
            with(binding) {
                when(trans.type) {
                    1 -> {
                        ivTransIcon.load(catExample.incomeList[trans.category - 1].icon)
                    }
                    2 -> {
                        ivTransIcon.load(catExample.expenseList[trans.category - 1].icon)
                    }
                    else -> {}
                }

                tvTransDateDay.text = trans.created_date.toString("dd")
                tvTransDateDayofweek.text = trans.created_date.toString("EEEE")
                tvTransDateMonthyear.text = trans.created_date.toString("MMM yyyy")

                tvTransName.text = trans.description
                tvTransAmount.text = trans.amount.toString()
                tvTransTime.text = trans.created_date.toString("hh:mm a")

                rlTransItem.setOnClickListener {
                    listener.onTransactionClick(trans)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionVH {
        val binding = ItemTransactionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TransactionVH(binding)
    }

    override fun onBindViewHolder(holder: TransactionVH, position: Int) {
        holder.bind(transList[position])
    }

    override fun getItemCount(): Int {
        return transList.size
    }
}