package com.example.movilessoftware2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movilessoftware2023a.adapter.RecamarasAdapter
import com.example.movilessoftware2023a.databinding.ActivityOpcionesBinding
import com.example.movilessoftware2023a.databinding.ActivityOpcionesBinding.inflate


class RecamarasRV : AppCompatActivity(){

    private lateinit var binding: ActivityOpcionesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerViewOpciones()
    }

    private fun initRecyclerViewOpciones(){
        binding.recyclerViewOpciones.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewOpciones.adapter = RecamarasAdapter(RecamarasProvider.OpcionesList)
    }

}