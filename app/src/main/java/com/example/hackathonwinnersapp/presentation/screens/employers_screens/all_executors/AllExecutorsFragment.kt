package com.example.hackathonwinnersapp.presentation.screens.employers_screens.all_executors

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathonwinnersapp.domain.models.executor.ExecutorDomainModel
import com.example.hackathonwinnersapp.presentation.adapterDelegates.executorAdapterDelegate
import com.example.hackathonwinnersapp.presentation.base.BaseFragment
import com.example.hackathonwinnersapp.presentation.diff_utils.ExecutorsDiffUtil
import com.example.hackathonwinnersapp.presentation.ui.dialog.initDialogToAddOrder
import com.example.hackathonwinnersapp.util.launchCollect
import com.example.hackathonwinnersapp.util.repeatOnStart
import com.example.hackatonwinnersapp.R
import com.example.hackatonwinnersapp.databinding.FragmentAllExecutorsBinding
import com.google.android.material.snackbar.Snackbar
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllExecutorsFragment : BaseFragment<AllExecutorsViewModel, FragmentAllExecutorsBinding>(
    FragmentAllExecutorsBinding::inflate
) {
    override val viewModel: AllExecutorsViewModel by viewModels()

    private var _executorAdapter: AsyncListDifferDelegationAdapter<ExecutorDomainModel>? = null

    private val executorAdapter get() = requireNotNull(_executorAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _executorAdapter = AsyncListDifferDelegationAdapter(
            ExecutorsDiffUtil(),
            executorAdapterDelegate(
                onTaxClick = { viewModel.onTaxClick(it) },
                onActionButtingClick = { viewModel.onActionButtonClick(it) }
            )
        )

        viewLifecycleOwner.repeatOnStart {
            launchCollect(viewModel.executors) { taxes ->
                executorAdapter.items = taxes
            }

            launchCollect(viewModel.errorMessage) { message ->
                message?.let { checkedMessage ->
                    Snackbar.make(binding.root, checkedMessage.toString(), Snackbar.LENGTH_SHORT)
                        .show()
                    viewModel.onErrorShown()
                }
            }
        }

        with(binding) {

            with(taxes) {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = executorAdapter
            }
        }
        setOnAddTaxesClick()
    }

    private fun setOnAddTaxesClick() {
        binding.addOrder.root.setOnClickListener {
            this.initDialogToAddOrder(
                title = getString(R.string.add_executor_title),
                firstTvHint = getString(R.string.add_executor_name),
                secondTvHint = getString(R.string.add_executor_iin)
            ) { name, iin ->
                //TODO dodelac'
            }
        }
    }
}