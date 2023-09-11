package com.example.movilessoftware2023a

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FormularioAvion : AppCompatActivity() {

    var accion: Accion = Accion.Editar
    var avion: Avion? = null
    val movimiento = CambioActividad(this)
    lateinit var mensajeSnackbar: Snackbar

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_avion)
        mensajeSnackbar = Snackbar(findViewById(R.id.txt_modo_avion))
        val botonGuardar = findViewById<Button>(R.id.btn_guardar_avion)
        val textViewModo = findViewById<TextView>(R.id.txt_modo_avion)

        accion = intent.getSerializableExtra("modo") as Accion
        textViewModo.text = accion.tipo

        if (accion == Accion.Editar) {
            val id = intent.getIntExtra("idAvion", -1)
            avion = intent.getParcelableExtra("avion")
            avion?.let {
                cargarDatosAvion(avion!!)
            }
        }
        botonGuardar.setOnClickListener {
            crearEditar()
            movimiento.cambiarActividad(ListaAvion::class.java)
        }
    }

    fun crearEditar() {
        val modelo = findViewById<EditText>(R.id.te_modelo_avion)
        val capacidadPasajeros = findViewById<EditText>(R.id.te_capacidad_avion)
        val numSerie = findViewById<EditText>(R.id.te_numserie_avion)
        val estado = findViewById<EditText>(R.id.te_estado_avion)
        val aerolinea = findViewById<EditText>(R.id.te_aerolinea_avion)

        if (
            modelo.text.isNotEmpty() &&
            capacidadPasajeros.text.isNotEmpty() &&
            numSerie.text.isNotEmpty() &&
            estado.text.isNotEmpty() &&
            aerolinea.text.isNotEmpty()
        ) {
            if (accion == Accion.Registrar) {
                crearAvion(
                    Avion(modelo = modelo.text.toString(),
                        capacidadPasajeros = capacidadPasajeros.text.toString().toInt(),
                        numSerie = numSerie.text.toString().toInt(),
                        estado = estado.text.toString(),
                        aerolinea = aerolinea.text.toString()))
                mensajeSnackbar.mostrar("Avión creado con éxito")
                finish()

            } /*else if (accion == Accion.Editar) {
                var id: Int = if (avion?.id != null) avion?.id!! else -1

                BaseDeDatos.actualizarAvion(
                    modelo = modelo.text.toString(),
                    capacidadPasajeros = capacidadPasajeros.text.toString().toInt(),
                    numSerie = numSerie.text.toString().toInt(),
                    estado = estado.text.toString(),
                    aerolinea = aerolinea.text.toString(),
                    id = id
                )
                mensajeSnackbar.mostrar("Avion actualizado con éxito")
                finish()
            }*/
        } else {
            mensajeSnackbar.mostrar("Revisar campos vacíos")
        }
    }

    fun cargarDatosAvion(avion: Avion) {
        val modelo = findViewById<EditText>(R.id.te_modelo_avion)
        val capacidadPasajeros = findViewById<EditText>(R.id.te_capacidad_avion)
        val numSerie = findViewById<EditText>(R.id.te_numserie_avion)
        val estado = findViewById<EditText>(R.id.te_estado_avion)
        val aerolinea = findViewById<EditText>(R.id.te_aerolinea_avion)
        modelo.setText(avion.modelo)
        capacidadPasajeros.setText(avion.capacidadPasajeros.toString())
        numSerie.setText(avion.numSerie.toString())
        estado.setText(avion.estado)
        aerolinea.setText(avion.aerolinea)
    }

    fun crearAvion(avion: Avion) {
        val avionMap = hashMapOf(
            "modelo" to avion.modelo,
            "capacidadPasajeros" to avion.capacidadPasajeros,
            "numSerie" to avion.numSerie,
            "estado" to avion.estado,
            "aerolinea" to avion.aerolinea
        )
        val db = Firebase.firestore
        db.collection("aviones")
            .add(avionMap)
            .addOnSuccessListener {
                finish()
            }.addOnFailureListener {
                mensajeSnackbar.mostrar("Error al guardar la Avion")
            }
    }
}
/*
    fun actualizarAvion(avion: Avion) {
        val desarrolladoraMap = hashMapOf(
            "nombre" to desarrolladora.nombre,
            "paginaWeb" to desarrolladora.paginaWeb,
            "anioCreacion" to desarrolladora.anioCreacion,
            "ubicacion" to desarrolladora.ubicacion,
            "esIndependiente" to desarrolladora.esIndependiente
        )
        val db = Firebase.firestore
        db.collection("desarrolladoras")
            .document(desarrolladora.id!!)
            .set(desarrolladoraMap)
            .addOnSuccessListener {
                generadorSnackbar.mostrar("Desarrolladora guardada con éxito")
                finish()
            }.addOnFailureListener {
                generadorSnackbar.mostrar("Error al guardar la desarrolladora")
            }
    }
}*/

