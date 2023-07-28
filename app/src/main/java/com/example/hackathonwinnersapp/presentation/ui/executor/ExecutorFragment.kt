package com.example.hackathonwinnersapp.presentation.ui.executor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hackathonwinnersapp.domain.model.ExecutorTabsData
import com.example.hackathonwinnersapp.presentation.ui.executor.tab.ExecutorTabType
import com.example.hackathonwinnersapp.presentation.utils.FragmentViewPagerAdapter
import com.example.hackatonwinnersapp.R
import com.example.hackatonwinnersapp.databinding.FragmentExecutorBinding
import com.google.android.material.tabs.TabLayoutMediator

class ExecutorFragment : Fragment() {

    private var _binding: FragmentExecutorBinding? = null
    val binding get() = requireNotNull(_binding)

    private lateinit var viewModel: ExecutorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExecutorBinding.inflate(inflater, container, false)
        return binding.root
    }

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
}