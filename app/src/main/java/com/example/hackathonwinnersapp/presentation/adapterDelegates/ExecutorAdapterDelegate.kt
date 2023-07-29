package com.example.hackathonwinnersapp.presentation.adapterDelegates

import com.example.hackathonwinnersapp.domain.models.executor.ExecutorDomainModel
import com.example.hackatonwinnersapp.databinding.ItemExecutorBinding
import com.hannesdorfmann.adapterdelegates4.dsl.AdapterDelegateViewBindingViewHolder
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

typealias ExecutorDelegateBindingViewHolder =
        AdapterDelegateViewBindingViewHolder<ExecutorDomainModel, ItemExecutorBinding>

fun executorAdapterDelegate(
    onTaxClick: ((ExecutorDomainModel) -> Unit),
    onActionButtingClick: ((ExecutorDomainModel) -> Unit)
) = adapterDelegateViewBinding<ExecutorDomainModel, ExecutorDomainModel, ItemExecutorBinding>(
    { layoutInflater, root ->
        ItemExecutorBinding.inflate(layoutInflater, root, false)
    }
) {
    initListenersExternally(onTaxClick, onActionButtingClick)
    bind { bindExternally() }
}

private fun ExecutorDelegateBindingViewHolder.initListenersExternally(
    onTaxClick: ((ExecutorDomainModel) -> Unit)? = null,
    onActionButtingClick: ((ExecutorDomainModel) -> Unit)? = null
) {
    with(binding) {
        root.setOnClickListener {
            onTaxClick?.invoke(item)
        }

        executorActionButton.setOnClickListener {
            onActionButtingClick?.invoke(item)
        }
    }
}

private fun ExecutorDelegateBindingViewHolder.bindExternally() {
    with(binding) {
        executorName.text = item.name
    }
}