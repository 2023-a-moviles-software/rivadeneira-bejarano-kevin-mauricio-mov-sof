package com.example.movilessoftware2023a.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movilessoftware2023a.Recamaras
import com.example.movilessoftware2023a.R


class RecamarasAdapter (private val opcionesList: List<Recamaras>) : RecyclerView.Adapter<RecamarasViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecamarasViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return RecamarasViewHolder(layoutInflater.inflate(R.layout.item_recamaras, parent, false))
    }

    override fun onBindViewHolder(holder: RecamarasViewHolder, position: Int) {
        val item = opcionesList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return opcionesList.size
    }
}