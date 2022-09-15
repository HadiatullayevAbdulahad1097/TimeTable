package com.example.timetable.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.timetable.Models.MyObject
import com.example.timetable.Adapter.HomeFragmentPagerAdapter
import com.example.timetable.R
import com.example.timetable.databinding.FragmentHomeBinding
import com.example.timetable.databinding.ItemTabBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var homeFragmentPagerAdapter: HomeFragmentPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.apply {

            floating.setOnClickListener {
                MyObject.isAdd = true
                findNavController().navigate(R.id.addTimeTable)
            }

            homeFragmentPagerAdapter = HomeFragmentPagerAdapter(this@HomeFragment)

            val listImage = ArrayList<Int>()
            listImage.add(R.drawable.image)
            listImage.add(R.drawable.image2)
            listImage.add(R.drawable.image3)
            listImage.add(R.drawable.image4)
            listImage.add(R.drawable.image5)
            listImage.add(R.drawable.image6)
            listImage.add(R.drawable.image7)
            listImage.add(R.drawable.image8)
            listImage.add(R.drawable.image9)
            listImage.add(R.drawable.image10)
            val random = Random().nextInt(listImage.size)
            imageView.setImageResource(listImage[random])

            viewPager2.adapter = homeFragmentPagerAdapter

            val list = ArrayList<Int>()
            list.add(R.drawable.ic_home)
            list.add(R.drawable.ic_sports)
            list.add(R.drawable.study)
            list.add(R.drawable.other)
            val list2 = ArrayList<Int>()
            list2.add(R.drawable.ic_home2)
            list2.add(R.drawable.ic_sports2)
            list2.add(R.drawable.study2)
            list2.add(R.drawable.other2)
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val custom = tab?.customView
                    custom?.findViewById<CardView>(R.id.neumorphicCardView)?.visibility =
                        View.VISIBLE
                    custom?.findViewById<ImageView>(R.id.icon)?.setImageResource(list[tab.position])
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    val custom = tab?.customView
                    custom?.findViewById<CardView>(R.id.neumorphicCardView)?.visibility =
                        View.INVISIBLE
                    custom?.findViewById<ImageView>(R.id.icon)
                        ?.setImageResource(list2[tab.position])
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })

            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                val itemTabBinding = ItemTabBinding.inflate(layoutInflater)

                itemTabBinding.icon.setImageResource(list2[position])

                itemTabBinding.neumorphicCardView.visibility = View.INVISIBLE

                tab.customView = itemTabBinding.root
            }.attach()
        }

        return binding.root
    }
}