package com.example.disneycodechallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.disneycodechallenge.data.Customer
import com.example.disneycodechallenge.databinding.ListItemBinding

class ReservationAdapter : RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>() {

    private var customerList: List<Customer>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReservationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        holder.bind(customerList!!)
    }

    override fun getItemCount(): Int {
        return if (customerList == null) 0
        else customerList?.size!!
    }

    fun setCustomerList(customerList: List<Customer>) {
        this.customerList = customerList
    }

    inner class ReservationViewHolder(binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val customerName = binding.customerName

        fun bind(data: List<Customer>) {
            customerName.text = data[adapterPosition].name
        }

    }

}