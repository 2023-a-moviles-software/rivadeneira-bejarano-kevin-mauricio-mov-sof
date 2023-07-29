package com.example.movilessoftware2023a


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movilessoftware2023a.adapter.AlojamientoAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AlojamientoProvider.AlojamientoLista
        initRecyclerViewRappi()

        val btnOpciones = findViewById<Button>(R.id.btn_consultar)
        btnOpciones.setOnClickListener {
            val launch = Intent(this, RecamarasRV::class.java)
            startActivity(launch)
            true
        }
    }

    private fun initRecyclerViewRappi(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_Alojamiento)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AlojamientoAdapter(AlojamientoProvider.AlojamientoLista)
    }
}