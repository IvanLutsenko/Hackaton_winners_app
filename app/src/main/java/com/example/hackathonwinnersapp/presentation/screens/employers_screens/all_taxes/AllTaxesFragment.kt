package com.example.hackathonwinnersapp.presentation.screens.employers_screens.all_taxes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathonwinnersapp.domain.models.taxes.TaxDomainModel
import com.example.hackathonwinnersapp.presentation.adapterDelegates.taxesAdapterDelegate
import com.example.hackathonwinnersapp.presentation.base.BaseFragment
import com.example.hackathonwinnersapp.presentation.diff_utils.TaxesDiffUtil
import com.example.hackathonwinnersapp.util.launchCollect
import com.example.hackathonwinnersapp.util.repeatOnStart
import com.example.hackatonwinnersapp.databinding.FragmentAllTaxesBinding
import com.google.android.material.snackbar.Snackbar
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllTaxesFragment : BaseFragment<AllTaxesViewModel, FragmentAllTaxesBinding>(
    FragmentAllTaxesBinding::inflate
) {
    override val viewModel: AllTaxesViewModel by viewModels()

    private var _taxesAdapter: AsyncListDifferDelegationAdapter<TaxDomainModel>? = null

    private val taxesAdapter get() = requireNotNull(_taxesAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _taxesAdapter = AsyncListDifferDelegationAdapter(
            TaxesDiffUtil(),
            taxesAdapterDelegate(
                onTaxClick = { viewModel.onTaxClick(it) },
                onActionButtingClick = { viewModel.onActionButtonClick(it) }
            )
        )

        viewLifecycleOwner.repeatOnStart {
            launchCollect(viewModel.taxes) { taxes ->
                taxesAdapter.items = taxes
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
                adapter = taxesAdapter
            }
        }
    }
}