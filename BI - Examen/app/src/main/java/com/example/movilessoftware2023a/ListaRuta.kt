package com.example.movilessoftware2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class ListaRuta : AppCompatActivity() {

    val movimiento: CambioActividad = CambioActividad(this)
    var accion: Accion = Accion.Registrar
    private var idRuta = -1
    private var idAvion = -1
    lateinit var adaptador: ArrayAdapter<Ruta>
    lateinit var avion: Avion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_ruta)


        val rutaTextView = findViewById<TextView>(R.id.textView_desarrolladora)

        idAvion = intent.getIntExtra("idAvion", -1)

        movimiento.callback = {
                intent ->
            intent.putExtra("modo", accion)
            intent.putExtra("idRuta", idRuta)
            intent.putExtra("idAvion", idAvion)
        }

        val rutas = ArrayList<Ruta>()
        if(idAvion != -1) {
            val avion = BaseDeDatos.buscarAvion(idAvion)
            if (avion != null) {
                this.avion = avion
                rutaTextView.text = avion.modelo
            }
        }

        val listView = findViewById<ListView>(R.id.lv_videojuegos)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            avion.rutas
        )

        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)
        val botonCrear = findViewById<Button>(R.id.btn_crear_videojuego)

        botonCrear.setOnClickListener {
            accion = Accion.Registrar
            movimiento.cambiarActividad(FormularioRuta::class.java)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_ruta, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idRuta = adaptador.getItem(id)?.id!!
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_opc_editar -> {
                accion = Accion.Editar
                movimiento.cambiarActividad(FormularioRuta::class.java)
                true
            }

            R.id.menu_item_eliminar -> {
                confirmarEliminar()
                true
            }

            else -> {
                super.onContextItemSelected(item)
            }
        }
    }

    fun confirmarEliminar() {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar la ruta?")
        builder.setPositiveButton("Si") { dialog, which ->
            val avion = BaseDeDatos.buscarAvion(idAvion)
            if(avion != null){
                avion.rutas.removeIf { it.id == idRuta }
                adaptador.notifyDataSetChanged()
            }
        }
        builder.setNegativeButton("No", null)
        val dialog = builder.create()
        dialog.show()
    }

    override fun onResume() {
        super.onResume()
        adaptador.notifyDataSetChanged()
    }

}