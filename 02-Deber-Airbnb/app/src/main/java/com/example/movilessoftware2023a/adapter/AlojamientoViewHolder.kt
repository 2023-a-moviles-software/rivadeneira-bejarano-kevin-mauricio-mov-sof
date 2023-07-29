package com.example.movilessoftware2023a.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movilessoftware2023a.R
import com.example.movilessoftware2023a.Alojamiento

class AlojamientoViewHolder (view: View): RecyclerView.ViewHolder(view) {

    val ubicacion = view.findViewById<TextView>(R.id.tvUbicacion)
    val distancia = view.findViewById<TextView>(R.id.tv_distancia)
    val fecha = view.findViewById<TextView>(R.id.tvFecha)
    val precio = view.findViewById<TextView>(R.id.tvPrecio)
    val photo = view.findViewById<ImageView>(R.id.ivRestaurante)


    fun render(restauranteModel: Alojamiento){
        ubicacion.text = restauranteModel.ubicacion
        distancia.text = restauranteModel.distancia
        fecha.text = restauranteModel.fecha
        precio.text = restauranteModel.precio
        Glide.with(photo.context).load(restauranteModel.photo).into(photo)

    }

}