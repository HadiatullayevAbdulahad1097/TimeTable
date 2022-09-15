package com.example.timetable.PagerFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.timetable.Adapter.RvAdapter
import com.example.timetable.Database.AppDataBase
import com.example.timetable.Models.MyObject
import com.example.timetable.Models.TimeTable
import com.example.timetable.R
import com.example.timetable.databinding.FragmentSportPageBinding

class SportPageFragment : Fragment() {
    lateinit var binding: FragmentSportPageBinding
    lateinit var list: ArrayList<TimeTable>
    lateinit var appDataBase: AppDataBase
    lateinit var rvAdapter: RvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSportPageBinding.inflate(layoutInflater)

        appDataBase = AppDataBase.getInstance(binding.root.context)

        list = ArrayList()

        for (i in appDataBase.biletDuo().getAllTimeTable()){
            if (i.type == "1"){
                list.add(i)
            }
        }

        rvAdapter = RvAdapter(list, object : RvAdapter.Click{
            @SuppressLint("NotifyDataSetChanged")
            override fun clickItem(timeTable: TimeTable, position: Int) {
                MyObject.timeTable = timeTable
                MyObject.position = position
                MyObject.pagerPosition = 1
                findNavController().navigate(R.id.insidePageFragment)
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun longClickItem(view: View, timeTable: TimeTable, position: Int) {
                val popupMenu = PopupMenu(binding.root.context,view)
                popupMenu.inflate(R.menu.menu)

                popupMenu.setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.edit->{
                            MyObject.isAdd = false
                            MyObject.timeTable = timeTable
                            findNavController().navigate(R.id.addTimeTable)
                        }
                        R.id.delete->{
                            appDataBase.biletDuo().deleteTimeTable(timeTable)
                            list.removeAt(position)
                            rvAdapter.notifyDataSetChanged()
                            Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show()
                        }
                    }
                    true
                }

                popupMenu.show()
            }
        })

        binding.rv.adapter = rvAdapter

        return binding.root
    }
}