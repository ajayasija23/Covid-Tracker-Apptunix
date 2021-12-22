package com.app.covidtracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.covidtracker.databinding.ItemSessionBinding
import com.app.covidtracker.databinding.ItemSlotBinding
import com.app.covidtracker.model.Session

class SlotAdapter(val mContext:Context, val list:List<String>):RecyclerView.Adapter<SlotAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlotAdapter.MyViewHolder {
        return MyViewHolder(
            ItemSlotBinding.inflate(
                LayoutInflater.from(mContext),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: SlotAdapter.MyViewHolder, position: Int) {
        holder.binding.tvSlot.text=list[position]
    }

    override fun getItemCount()=list.size
    inner class MyViewHolder(val binding:ItemSlotBinding):RecyclerView.ViewHolder(binding.root)
}