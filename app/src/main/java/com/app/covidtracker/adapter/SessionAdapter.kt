package com.app.covidtracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.covidtracker.databinding.ItemSessionBinding
import com.app.covidtracker.model.Session

class SessionAdapter(val mContext:Context,val list:List<Session>):RecyclerView.Adapter<SessionAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionAdapter.MyViewHolder {
        return MyViewHolder(
            ItemSessionBinding.inflate(
                LayoutInflater.from(mContext),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: SessionAdapter.MyViewHolder, position: Int) {
        holder.binding.tvVaccineName.text=list[position].vaccine
        holder.binding.tvAddress.text=list[position].address+", "+list[position].name+" "+list[position].block_name
        holder.binding.rvSlots.adapter=SlotAdapter(mContext,list[position].slots)
    }

    override fun getItemCount()=list.size
    inner class MyViewHolder(val binding:ItemSessionBinding):RecyclerView.ViewHolder(binding.root)
}