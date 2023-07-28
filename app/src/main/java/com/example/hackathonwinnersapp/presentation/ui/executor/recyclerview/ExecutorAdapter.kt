package com.example.hackathonwinnersapp.presentation.ui.executor.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathonwinnersapp.domain.model.ExecutorModel
import com.example.hackatonwinnersapp.databinding.ItemExecutorBinding

class ExecutorAdapter(private val dataList: List<ExecutorModel>) :
    RecyclerView.Adapter<ExecutorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExecutorViewHolder =
        ExecutorViewHolder(
            ItemExecutorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ExecutorViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}

class ExecutorViewHolder(itemView: ItemExecutorBinding) : RecyclerView.ViewHolder(itemView.root) {

    fun bind(item: ExecutorModel) {

    }
}