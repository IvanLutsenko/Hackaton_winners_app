package com.example.hackathonwinnersapp.presentation.main.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hackathonwinnersapp.presentation.utils.IS_ORDER
import com.example.hackathonwinnersapp.presentation.utils.IS_TAXES

class MainViewPagerAdapter(
    activity: AppCompatActivity,
    private val itemCount: Int
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = itemCount

    override fun createFragment(position: Int): Fragment =
        when (position) {
            IS_ORDER -> {/*TODO First Fragment*/}
            IS_TAXES -> {/*Todo Second Fragment*/}
            else -> throw RuntimeException("Invalid position: $position")
        }
}