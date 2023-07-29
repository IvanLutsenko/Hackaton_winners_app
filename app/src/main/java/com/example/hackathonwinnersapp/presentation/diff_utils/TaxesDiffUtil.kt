package com.example.hackathonwinnersapp.presentation.diff_utils

import androidx.recyclerview.widget.DiffUtil
import com.example.hackathonwinnersapp.domain.models.taxes.TaxDomainModel

class TaxesDiffUtil : DiffUtil.ItemCallback<TaxDomainModel>() {
    override fun areItemsTheSame(
        oldItem: TaxDomainModel,
        newItem: TaxDomainModel
    ): Boolean {
        return oldItem.name == newItem.name
    }


    override fun areContentsTheSame(
        oldItem: TaxDomainModel,
        newItem: TaxDomainModel
    ): Boolean {
        return oldItem == newItem
    }
}