package com.example.hackathonwinnersapp.presentation.adapterDelegates

import android.view.View
import androidx.core.content.ContextCompat
import com.example.hackathonwinnersapp.data.network.models.EmployeeDataModel
import com.example.hackathonwinnersapp.domain.Enums.OrderStatus
import com.example.hackathonwinnersapp.domain.Enums.OrderStatus.CANCELLED
import com.example.hackathonwinnersapp.domain.Enums.OrderStatus.FULFILLED
import com.example.hackathonwinnersapp.domain.Enums.OrderStatus.IN_PROGRESS
import com.example.hackathonwinnersapp.domain.Enums.OrderStatus.NEW
import com.example.hackathonwinnersapp.domain.Enums.OrderStatus.PAYED
import com.example.hackathonwinnersapp.domain.Enums.OrderStatus.UNIDENTIFIED
import com.example.hackathonwinnersapp.domain.models.orders.OrderDomainModel
import com.example.hackatonwinnersapp.R
import com.example.hackatonwinnersapp.databinding.ItemOrderBinding
import com.hannesdorfmann.adapterdelegates4.dsl.AdapterDelegateViewBindingViewHolder
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

typealias OrdersDelegateBindingViewHolder =
        AdapterDelegateViewBindingViewHolder<OrderDomainModel, ItemOrderBinding>

fun ordersAdapterDelegate(
    onEmployeeClick: ((EmployeeDataModel) -> Unit),
    onOrderClick: ((OrderDomainModel) -> Unit),
    onActionButtonClick: ((OrderDomainModel) -> Unit)
) = adapterDelegateViewBinding<OrderDomainModel, OrderDomainModel, ItemOrderBinding>(
    { layoutInflater, root ->
        ItemOrderBinding.inflate(layoutInflater, root, false)
    }
) {
    initListenersExternally(onEmployeeClick, onOrderClick, onActionButtonClick)
    bind { bindExternally() }
}

private fun OrdersDelegateBindingViewHolder.initListenersExternally(
    onEmployeeClick: ((EmployeeDataModel) -> Unit)? = null,
    onOrderClick: ((OrderDomainModel) -> Unit)? = null,
    onActionButtonClick: ((OrderDomainModel) -> Unit)? = null
) {
    with(binding) {
        root.setOnClickListener {
            onOrderClick?.invoke(item)
        }

        employeeName.setOnClickListener {
            onEmployeeClick?.invoke(item.employee)
        }

        orderActionButton.setOnClickListener {
            onActionButtonClick?.invoke(item)
        }
    }
}

private fun OrdersDelegateBindingViewHolder.bindExternally() {
    with(binding) {
        orderName.text = item.name
        employeeName.text = item.employee.name
        orderStatus.text = OrderStatus.getName(item.status)
        orderStatus.setTextColor(
            ContextCompat.getColor(context, getColorByStatus(item.status))
        )
        val actionButtonText = setActionButtonText(item.status)
        actionButtonText?.let { orderActionButton.text = it }
            ?: run { orderActionButton.visibility = View.INVISIBLE }
        setActionButtonText(item.status)?.let { orderActionButton.text = it }
    }
}

private fun setActionButtonText(status: OrderStatus) = when (status) {
    NEW -> "назначить"
    FULFILLED -> "оплатить"
    else -> null
}

private fun getColorByStatus(status: OrderStatus) =
    when (status) {
        NEW -> R.color.blue
        CANCELLED -> R.color.red
        FULFILLED -> R.color.colorPrimary
        UNIDENTIFIED -> R.color.red
        IN_PROGRESS -> R.color.grey
        PAYED -> R.color.colorPrimaryDark
    }