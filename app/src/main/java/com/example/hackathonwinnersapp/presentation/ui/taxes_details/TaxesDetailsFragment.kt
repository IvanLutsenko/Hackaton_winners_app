package com.example.hackathonwinnersapp.presentation.ui.taxes_details

import android.os.Bundle
import com.example.hackathonwinnersapp.presentation.ui.dialog.BaseDialog
import com.example.hackatonwinnersapp.databinding.FragmentTaxesDetailsBinding

class TaxesDetailsFragment : BaseDialog<FragmentTaxesDetailsBinding>(
    FragmentTaxesDetailsBinding::inflate
) {

    override fun setUi() {
        super.setUi()

        val bundle = this.arguments
        if (bundle != null) with(binding) {
            taxesDetailsName.text = bundle.getString(TAX_DETAILS_NAME)
            val taxesDetailsPaymentText =
                if (bundle.getString(TAX_DETAILS_PAYED) == "true") "оплачено" else "неоплачено"
                    .plus(" ")
                    .plus(bundle.getString(TAX_DETAILS_SUM))
            taxesDetailsPayment.text = taxesDetailsPaymentText
        }

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.closeDetailsButton.setOnClickListener {
            dismiss()
        }
    }

    companion object {

        const val TAX_DETAILS_NAME = "TAX_DETAILS_NAME"
        const val TAX_DETAILS_PAYED = "TAX_DETAILS_PAYED"
        const val TAX_DETAILS_SUM = "TAX_DETAILS_SUM"

        fun build(name: String, isPayed: Boolean, sum: String) = TaxesDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(TAX_DETAILS_NAME, name)
                putBoolean(TAX_DETAILS_PAYED, isPayed)
                putString(TAX_DETAILS_SUM, sum)
            }
        }
    }
}