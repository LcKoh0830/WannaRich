package com.example.wannarich.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.wannarich.R
import com.example.wannarich.databinding.FragmentCalendarBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalendarFragment : Fragment(R.layout.fragment_calendar) {

    private lateinit var binding: FragmentCalendarBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentCalendarBinding.bind(view)

    }


}