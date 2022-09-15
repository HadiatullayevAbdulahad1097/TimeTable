package com.example.timetable.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timetable.Models.TimeWork
import com.example.timetable.databinding.ItemInsideBinding

class RvInsideAdapter(var list: List<TimeWork>, var click: Click) : RecyclerView.Adapter<RvInsideAdapter.Vh>() {
    inner class Vh(var itemRv: ItemInsideBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(timeWork: TimeWork, position: Int) {
            itemRv.data.text = timeWork.date
            itemRv.tvName.text = timeWork.name
            itemRv.from.text = timeWork.from
            itemRv.until.text = timeWork.until
            itemRv.card.setOnClickListener {
                click.clickItem(timeWork, position)
            }
            itemRv.popup.setOnClickListener {
                click.clickPopup(itemRv.popup, timeWork, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            ItemInsideBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int = list.size

    interface Click{
        fun clickItem(timeWork: TimeWork, position: Int)
        fun clickPopup(view: View, timeWork: TimeWork, position: Int)
    }
}