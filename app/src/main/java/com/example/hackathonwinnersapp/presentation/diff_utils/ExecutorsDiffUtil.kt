package com.example.hackathonwinnersapp.presentation.diff_utils

import androidx.recyclerview.widget.DiffUtil
import com.example.hackathonwinnersapp.domain.models.executor.ExecutorDomainModel

class ExecutorsDiffUtil : DiffUtil.ItemCallback<ExecutorDomainModel>() {
    override fun areItemsTheSame(
        oldItem: ExecutorDomainModel,
        newItem: ExecutorDomainModel
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: ExecutorDomainModel,
        newItem: ExecutorDomainModel
    ): Boolean {
        return oldItem == newItem
    }
}