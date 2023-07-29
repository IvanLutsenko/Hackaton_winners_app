package com.example.hackathonwinnersapp.presentation.ui.countTaxes

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathonwinnersapp.domain.model.ExecutorModel
import com.example.hackathonwinnersapp.domain.model.ExecutorStatus
import com.example.hackathonwinnersapp.domain.models.executor.ExecutorDomainModel
import com.example.hackathonwinnersapp.presentation.ui.dialog.BaseDialog
import com.example.hackathonwinnersapp.presentation.ui.dialog.initDialogToAddExecutor
import com.example.hackathonwinnersapp.presentation.ui.executor.recyclerview.ExecutorAdapter
import com.example.hackathonwinnersapp.presentation.ui.taxes_details.TaxesDetailsFragment
import com.example.hackatonwinnersapp.databinding.FragmentCountTaxesBinding

class CountTaxesFragment : BaseDialog<FragmentCountTaxesBinding>(
    FragmentCountTaxesBinding::inflate
) {

    override fun setUi() {
        super.setUi()

        var name = ""
        var payed = ""
        var sum = ""

        val bundle = this.arguments
        if (bundle != null) {
            name = bundle.getString(TaxesDetailsFragment.TAX_DETAILS_NAME).toString()
            payed = bundle.getString(TaxesDetailsFragment.TAX_DETAILS_PAYED).toString()
            sum = bundle.getString(TaxesDetailsFragment.TAX_DETAILS_SUM).toString()
        }

        binding.countTaxesRv.layoutManager = LinearLayoutManager(binding.root.context)
        binding.countTaxesRv.adapter =
            ExecutorAdapter(listOf(ExecutorModel(name, ExecutorStatus.IN_PROGRESS)))
    }

    private fun setOnClickListener() {
        binding.addTaxesButton.setOnClickListener {
            this.initDialogToAddExecutor(listOf(ExecutorDomainModel("1", "Harry"))) {
                //Todo send data
            }
        }
        binding.saveTaxesButton.setOnClickListener {
            //Todo send data and close
        }
        binding.cancelTaxesButton.setOnClickListener { dismiss() }
    }

    companion object {

        const val TAX_DETAILS_NAME = "TAX_DETAILS_NAME"
        const val TAX_DETAILS_PAYED = "TAX_DETAILS_PAYED"
        const val TAX_DETAILS_SUM = "TAX_DETAILS_SUM"

        fun build(name: String, isPayed: Boolean, sum: String) = CountTaxesFragment().apply {
            arguments = Bundle().apply {
                putString(TAX_DETAILS_NAME, name)
                putBoolean(TAX_DETAILS_PAYED, isPayed)
                putString(TAX_DETAILS_SUM, sum)
            }
        }
    }
}