package com.example.movilessoftware2023a.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movilessoftware2023a.R
import com.example.movilessoftware2023a.Alojamiento

class AlojamientoAdapter(private val alojamientoList: List<Alojamiento>) : RecyclerView.Adapter<AlojamientoViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlojamientoViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return AlojamientoViewHolder(layoutInflater.inflate(R.layout.item_alojamiento, parent, false))
    }

    override fun onBindViewHolder(holder: AlojamientoViewHolder, position: Int) {
        val item = alojamientoList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return alojamientoList.size
    }

}