package com.example.hackathonwinnersapp.presentation.screens.employers_screens.all_orders

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathonwinnersapp.domain.models.orders.OrderDomainModel
import com.example.hackathonwinnersapp.domain.models.orders.OrderRequestModel
import com.example.hackathonwinnersapp.presentation.adapterDelegates.ordersAdapterDelegate
import com.example.hackathonwinnersapp.presentation.base.BaseFragment
import com.example.hackathonwinnersapp.presentation.diff_utils.OrdersDiffUtil
import com.example.hackathonwinnersapp.presentation.ui.dialog.initDialogToAddOrder
import com.example.hackathonwinnersapp.presentation.ui.executor.ExecutorFragment
import com.example.hackathonwinnersapp.util.launchCollect
import com.example.hackathonwinnersapp.util.repeatOnStart
import com.example.hackatonwinnersapp.R
import com.example.hackatonwinnersapp.databinding.FragmentAllOrdersBinding
import com.google.android.material.snackbar.Snackbar
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllOrdersFragment : BaseFragment<AllOrdersViewModel, FragmentAllOrdersBinding>(
    FragmentAllOrdersBinding::inflate
) {

    override val viewModel: AllOrdersViewModel by viewModels()

    private var _ordersAdapter: AsyncListDifferDelegationAdapter<OrderDomainModel>? = null
    private val ordersAdapter get() = requireNotNull(_ordersAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _ordersAdapter = AsyncListDifferDelegationAdapter(
            OrdersDiffUtil(),
            ordersAdapterDelegate(
                onOrderClick = { viewModel.onOrderClick(it) },
                onEmployeeClick = { onEmployeeClick() },
                onActionButtonClick = { viewModel.onActionButtonClick(it) }
            )
        )

        viewLifecycleOwner.repeatOnStart {
            launchCollect(viewModel.orders) { orders ->
                ordersAdapter.items = orders
            }

            launchCollect(viewModel.errorMessage) { message ->
                message?.let { checkedMessage ->
                    Snackbar.make(binding.root, checkedMessage.toString(), Snackbar.LENGTH_SHORT)
                        .show()
                    viewModel.onErrorShown()
                }
            }

            with(binding) {
                with(orders) {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = ordersAdapter
                }
            }
        }

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.addOrder.root.setOnClickListener {
            this.initDialogToAddOrder(
                title = getString(R.string.order_dialog_title),
                firstTvHint = getString(R.string.order_dialog_name_title),
                secondTvHint = getString(R.string.order_dialog_cost_title)
            ) { name, cost ->
                viewModel.addOrder(OrderRequestModel(name, cost.toInt()))
            }
        }
    }

    private fun onEmployeeClick() {
        ExecutorFragment.build().show(childFragmentManager, null)
    }
}