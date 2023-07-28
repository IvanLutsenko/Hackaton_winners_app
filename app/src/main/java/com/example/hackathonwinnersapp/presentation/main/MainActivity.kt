package com.example.hackathonwinnersapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hackathonwinnersapp.presentation.main.adapter.MainViewPagerAdapter
import com.example.hackathonwinnersapp.presentation.utils.IS_ORDER
import com.example.hackathonwinnersapp.presentation.utils.IS_TAXES
import com.example.hackathonwinnersapp.presentation.utils.viewPagerFragmentsList
import com.example.hackatonwinnersapp.R
import com.example.hackatonwinnersapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

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
        val tabLayoutMediator =
            TabLayoutMediator(binding.mainTabLayout, binding.mainViewPager) { tab, position ->
                tab.text = when (position) {
                    IS_ORDER -> resources.getString(R.string.all_orders)
                    IS_TAXES -> resources.getString(R.string.all_taxes)
                    else -> String.toString()
                }
            }
        binding.mainViewPager.adapter = MainViewPagerAdapter(this, viewPagerFragmentsList.size)
        tabLayoutMediator.attach()
    }
}