package com.example.hackathonwinnersapp.presentation.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hackathonwinnersapp.presentation.ui.executor.tab.ExecutorTabFragment
import com.example.hackathonwinnersapp.presentation.ui.executor.tab.ExecutorTabFragment.Companion.EXECUTOR_TAB_TYPE_TAG
import com.example.hackathonwinnersapp.presentation.ui.executor.tab.ExecutorTabsData

class FragmentViewPagerAdapter(
    fragment: Fragment,
    private val items: List<ExecutorTabsData>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment = ExecutorTabFragment().apply {
        arguments = Bundle().apply { putString(EXECUTOR_TAB_TYPE_TAG, items[position].type.name) }
    }
}