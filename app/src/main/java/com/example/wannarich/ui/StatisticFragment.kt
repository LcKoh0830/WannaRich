package com.example.wannarich.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.wannarich.R
import com.example.wannarich.databinding.FragmentStatisticBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticFragment : Fragment(R.layout.fragment_statistic) {

    private lateinit var binding: FragmentStatisticBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentStatisticBinding.bind(view)
    }
}