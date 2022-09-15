package com.example.timetable.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timetable.Models.TimeTable
import com.example.timetable.databinding.ItemRvBinding

class RvAdapter(val list: ArrayList<TimeTable>, val click: Click) :
    RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(timeTable: TimeTable, position: Int) {
            itemRv.data.text = timeTable.date
            itemRv.from.text = timeTable.fromTime
            itemRv.until.text = timeTable.untilTime
            itemRv.card.setOnClickListener {
                click.clickItem(timeTable, position)
            }
            itemRv.popup.setOnClickListener {
                click.longClickItem(itemRv.popup, timeTable, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int = list.size
    interface Click{
        fun clickItem(timeTable: TimeTable, position: Int)
        fun longClickItem(view: View, timeTable: TimeTable, position: Int)
    }
}