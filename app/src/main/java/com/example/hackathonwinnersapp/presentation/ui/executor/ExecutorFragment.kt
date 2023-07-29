package com.example.hackathonwinnersapp.presentation.ui.executor

import android.os.Bundle
import android.view.View
import com.example.hackathonwinnersapp.domain.model.ExecutorTabsData
import com.example.hackathonwinnersapp.presentation.ui.dialog.BaseDialog
import com.example.hackathonwinnersapp.presentation.ui.executor.tab.ExecutorTabType
import com.example.hackathonwinnersapp.presentation.utils.FragmentViewPagerAdapter
import com.example.hackatonwinnersapp.R
import com.example.hackatonwinnersapp.databinding.FragmentExecutorBinding
import com.google.android.material.tabs.TabLayoutMediator

class ExecutorFragment : BaseDialog<FragmentExecutorBinding>(FragmentExecutorBinding::inflate) {

    private lateinit var viewModel: ExecutorViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabLayout()
    }

    private fun initTabLayout() {
        val tabLayoutMediator: TabLayoutMediator

        val viewPagerFragmentsList = listOf(
            ExecutorTabsData(resources.getString(R.string.executor_orders), ExecutorTabType.ORDER),
            ExecutorTabsData(
                resources.getString(R.string.executor_payments),
                ExecutorTabType.ORDER
            ),
            ExecutorTabsData(resources.getString(R.string.executor_taxes), ExecutorTabType.ORDER)
        ).apply {
            tabLayoutMediator = TabLayoutMediator(
                binding.executorTabs.tabLayout,
                binding.executorTabs.viewPager
            ) { tab, position -> tab.text = this[position].title }
        }

        binding.executorTabs.viewPager.adapter =
            FragmentViewPagerAdapter(this, viewPagerFragmentsList)

        tabLayoutMediator.attach()
    }

    companion object {

//        const val TAX_DETAILS_NAME = "TAX_DETAILS_NAME"
//        const val TAX_DETAILS_PAYED = "TAX_DETAILS_PAYED"
//        const val TAX_DETAILS_SUM = "TAX_DETAILS_SUM"

        fun build(/*name: String, isPayed: Boolean, sum: String*/) = ExecutorFragment().apply {
            arguments = Bundle().apply {
//                putString(TAX_DETAILS_NAME, name)
//                putBoolean(TAX_DETAILS_PAYED, isPayed)
//                putString(TAX_DETAILS_SUM, sum)
            }
        }
    }
}