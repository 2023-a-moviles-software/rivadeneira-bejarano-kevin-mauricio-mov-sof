package com.example.movilessoftware2023a.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movilessoftware2023a.R
import com.example.movilessoftware2023a.Recamaras

class RecamarasViewHolder (view: View): RecyclerView.ViewHolder(view){

    val nombreRecamara = view.findViewById<TextView>(R.id.tvOpcionesNombre)
    val camas = view.findViewById<TextView>(R.id.tvCamas)
    val banios = view.findViewById<TextView>(R.id.tvBanios)
    val photoRecamara = view.findViewById<ImageView>(R.id.ivRecamaras)

    fun render(opcionesModel: Recamaras){
        nombreRecamara.text = opcionesModel.nombreRecamara
        camas.text = opcionesModel.camas
        banios.text = opcionesModel.banios
        Glide.with(photoRecamara.context).load(opcionesModel.photo).into(photoRecamara)

    }

}