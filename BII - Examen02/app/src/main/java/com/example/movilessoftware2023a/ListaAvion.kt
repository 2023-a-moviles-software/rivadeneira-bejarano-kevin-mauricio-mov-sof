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
import androidx.appcompat.app.AlertDialog

class ListaAvion : AppCompatActivity() {
    var idSeleccionado = 0
    var accion = Accion.Registrar
    val movimiento = CambioActividad(this)
    lateinit var msgSnackbar: Snackbar
    lateinit var adaptador: ArrayAdapter<Avion>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        msgSnackbar = Snackbar(findViewById(R.id.lv_aviones))

        movimiento.callback = {
            intent ->
            intent.putExtra("idAvion",idSeleccionado)
            intent.putExtra("modo", accion)
        }

        val listView = findViewById<ListView>(R.id.lv_aviones)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BaseDeDatos.aviones
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonCrear = findViewById<Button>(R.id.btn_crear_avion)
        botonCrear.setOnClickListener {
            accion = Accion.Registrar
            movimiento.cambiarActividad(FormularioAvion::class.java)
        }

        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_avion, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idSeleccionado = adaptador.getItem(id)?.id!!
    }

    fun confirmarEliminar() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar el avión?")
        builder.setPositiveButton("Si") { dialog, which ->
            if(BaseDeDatos.eliminarAvion(idSeleccionado)){
                adaptador.notifyDataSetChanged()
            }
        }
        builder.setNegativeButton("No", null)

        val dialog = builder.create()
        dialog.show()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_opc_editar -> {
                accion = Accion.Editar
                movimiento.cambiarActividad(FormularioAvion::class.java)
                true
            }

            R.id.menu_item_eliminar -> {
                confirmarEliminar()
                true
            }

            R.id.menu_item_ver_rutas -> {
                movimiento.cambiarActividad(ListaRuta::class.java)
                true
            }

            else -> {
                super.onContextItemSelected(item)
            }
        }

    }

    override fun onRestart() {
        super.onRestart()
        adaptador.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        adaptador.notifyDataSetChanged()
    }
}