package com.example.wannarich.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.wannarich.R
import com.example.wannarich.databinding.FragmentTransactionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionFragment : Fragment(R.layout.fragment_transaction) {

    private lateinit var binding: FragmentTransactionBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentTransactionBinding.bind(view)


    }
}