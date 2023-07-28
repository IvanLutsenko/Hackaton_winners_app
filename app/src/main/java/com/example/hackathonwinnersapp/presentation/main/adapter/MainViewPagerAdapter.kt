package com.example.hackathonwinnersapp.presentation.main.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainViewPagerAdapter(
    activity: AppCompatActivity,
    private val items: List<Fragment>
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment = items[position]
}