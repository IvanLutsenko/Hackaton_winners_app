package com.example.hackathonwinnersapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hackathonwinnersapp.presentation.screens.employers_screens.all_executors.AllExecutorsFragment
import com.example.hackathonwinnersapp.presentation.screens.employers_screens.all_orders.AllOrdersFragment
import com.example.hackathonwinnersapp.presentation.screens.employers_screens.all_taxes.AllTaxesFragment
import com.example.hackathonwinnersapp.presentation.utils.ActivityViewPagerAdapter
import com.example.hackatonwinnersapp.R
import com.example.hackatonwinnersapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    val binding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initTabLayout()
    }

    private fun initTabLayout() {
        listOf(
            AllOrdersFragment(),
            AllTaxesFragment(),
            AllExecutorsFragment()
        ).apply {
            val tabLayoutMediator = TabLayoutMediator(
                binding.mainTabsLayout.tabLayout,
                binding.mainTabsLayout.viewPager
            ) { tab, position ->
                tab.text = when (position) {
                    MainTabType.ORDER.ordinal -> resources.getString(R.string.all_orders)
                    MainTabType.TAXES.ordinal -> resources.getString(R.string.all_taxes)
                    MainTabType.EXECUTORS.ordinal -> resources.getString(R.string.all_executors)
                    else -> String.toString()
                }
            }

            binding.mainTabsLayout.viewPager.adapter =
                ActivityViewPagerAdapter(this@MainActivity, this)
            tabLayoutMediator.attach()
        }
    }
}