package com.example.hackathonwinnersapp.presentation.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ActivityViewPagerAdapter(
    fragment: AppCompatActivity,
    private val items: List<Fragment>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment = items[position]
}