package com.example.timetable.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.timetable.Models.MyObject
import com.example.timetable.Models.TimeWork
import com.example.timetable.SqlDB.MyDbHelper
import com.example.timetable.Adapter.RvInsideAdapter
import com.example.timetable.R
import com.example.timetable.databinding.FragmentInsidePageBinding
import java.util.*

class InsidePageFragment : Fragment() {
    lateinit var binding: FragmentInsidePageBinding
    lateinit var rvInsideAdapter: RvInsideAdapter
    lateinit var list: ArrayList<TimeWork>
    lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsidePageBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(binding.root.context)

        binding.apply {

            list = ArrayList()

            for (i in myDbHelper.readTimeWork()) {
                if (i.pagerType == MyObject.pagerPosition) {
                    if (MyObject.position == i.type) {
                        list.add(i)
                    }
                }
            }

            rvInsideAdapter = RvInsideAdapter(list, object : RvInsideAdapter.Click {
                override fun clickItem(timeWork: TimeWork, position: Int) {

                }

                @SuppressLint("NotifyDataSetChanged")
                override fun clickPopup(view: View, timeWork: TimeWork, position: Int) {
                    val popupMenu = PopupMenu(binding.root.context, view)
                    popupMenu.inflate(R.menu.menu)

                    popupMenu.setOnMenuItemClickListener {
                        when (it.itemId) {
                            R.id.edit -> {
                                MyObject.isAddTimeWork = false
                                MyObject.TimeWork = timeWork
                                findNavController().navigate(R.id.addTimeWorkFragment)
                            }
                            R.id.delete -> {
                                myDbHelper.deleteTimeWork(timeWork)
                                list.removeAt(position)
                                rvInsideAdapter.notifyDataSetChanged()
                                Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show()
                            }
                        }
                        true
                    }

                    popupMenu.show()
                }
            })

            rv.adapter = rvInsideAdapter

            val listImage = ArrayList<Int>()
            listImage.add(R.drawable.image22)
            listImage.add(R.drawable.image23)
            listImage.add(R.drawable.image24)
            listImage.add(R.drawable.image25)
            listImage.add(R.drawable.image21)
            listImage.add(R.drawable.image26)
            listImage.add(R.drawable.image9)
            listImage.add(R.drawable.image27)
            listImage.add(R.drawable.image28)
            listImage.add(R.drawable.image30)
            listImage.add(R.drawable.image29)
            listImage.add(R.drawable.image10)
            val random = Random().nextInt(listImage.size)
            image.setImageResource(listImage[random])

            floating.setOnClickListener {
                MyObject.isAddTimeWork = true
                findNavController().navigate(R.id.addTimeWorkFragment)
            }
        }
        return binding.root
    }
}