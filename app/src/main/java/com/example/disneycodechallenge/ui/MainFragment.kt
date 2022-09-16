package com.example.disneycodechallenge.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.disneycodechallenge.R
import com.example.disneycodechallenge.adapter.ReservationAdapter
import com.example.disneycodechallenge.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding
    private lateinit var recyclerAdapter: ReservationAdapter
    private val viewModel by viewModels<MainViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        initRecyclerView()
        initViewModel()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel() {
        viewModel.getLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                recyclerAdapter.setCustomerList(it)
                recyclerAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initRecyclerView() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        binding?.reservationRecyclerView?.layoutManager = layoutManager
        recyclerAdapter = ReservationAdapter()
        binding?.reservationRecyclerView?.adapter = recyclerAdapter
    }


}