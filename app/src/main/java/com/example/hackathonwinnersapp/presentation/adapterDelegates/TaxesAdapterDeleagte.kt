package com.example.hackathonwinnersapp.presentation.adapterDelegates

import androidx.core.view.isInvisible
import com.example.hackathonwinnersapp.domain.models.taxes.TaxDomainModel
import com.example.hackatonwinnersapp.databinding.ItemTaxBinding
import com.hannesdorfmann.adapterdelegates4.dsl.AdapterDelegateViewBindingViewHolder
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

typealias TaxesDelegateBindingViewHolder =
        AdapterDelegateViewBindingViewHolder<TaxDomainModel, ItemTaxBinding>

fun taxesAdapterDelegate(
    onTaxClick: ((TaxDomainModel) -> Unit),
    onActionButtingClick: ((TaxDomainModel) -> Unit)
) = adapterDelegateViewBinding<TaxDomainModel, TaxDomainModel, ItemTaxBinding>(
    { layoutInflater, root ->
        ItemTaxBinding.inflate(layoutInflater, root, false)
    }
) {
    initListenersExternally(onTaxClick, onActionButtingClick)
    bind { bindExternally() }
}

private fun TaxesDelegateBindingViewHolder.initListenersExternally(
    onTaxClick: ((TaxDomainModel) -> Unit)? = null,
    onActionButtingClick: ((TaxDomainModel) -> Unit)? = null
) {
    with(binding) {
        root.setOnClickListener {
            onTaxClick?.invoke(item)
        }

        taxActionButton.setOnClickListener {
            onActionButtingClick?.invoke(item)
        }
    }
}

private fun TaxesDelegateBindingViewHolder.bindExternally() {
    with(binding) {
        taxName.text = item.name
        taxSum.text = item.sum
        taxStatus.text = if (item.isPayed) "оплачено" else "не оплачено"
        taxActionButton.isInvisible = item.isPayed
    }
}