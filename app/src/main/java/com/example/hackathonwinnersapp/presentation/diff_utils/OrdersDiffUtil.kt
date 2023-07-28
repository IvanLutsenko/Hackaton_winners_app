package com.example.hackathonwinnersapp.presentation.diff_utils

import androidx.recyclerview.widget.DiffUtil
import com.example.hackathonwinnersapp.domain.models.orders.OrderDomainModel

class OrdersDiffUtil : DiffUtil.ItemCallback<OrderDomainModel>() {
    override fun areItemsTheSame(
        oldItem: OrderDomainModel,
        newItem: OrderDomainModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: OrderDomainModel,
        newItem: OrderDomainModel
    ): Boolean {
        return oldItem == newItem
    }
}