package com.example.timetable.Adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.timetable.PagerFragments.HousePageFragment
import com.example.timetable.PagerFragments.OtherPagerFragment
import com.example.timetable.PagerFragments.SportPageFragment
import com.example.timetable.PagerFragments.StudyPageFragment

class HomeFragmentPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment = when(position){
        0 -> HousePageFragment()
        1 -> SportPageFragment()
        2 -> StudyPageFragment()
        else -> OtherPagerFragment()
    }
}