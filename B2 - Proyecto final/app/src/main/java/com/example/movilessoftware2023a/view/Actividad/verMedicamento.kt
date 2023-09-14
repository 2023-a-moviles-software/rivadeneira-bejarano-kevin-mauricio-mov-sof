package com.example.app_proyecto2.view.Actividad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.app_proyecto2.R
import com.example.app_proyecto2.entities.Medicamento
import com.example.app_proyecto2.entities.Observaciones
import com.example.app_proyecto2.entities.Tratamiento
import com.example.app_proyecto2.view.Lista.verTratamientos
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat

class verMedicamento : AppCompatActivity() {

    // Referencia Firestore
    val db = Firebase.firestore

    // Listas
    val listaPrioridades: MutableList<String> = ArrayList()
    val listaEtiquetas: MutableList<String> = ArrayList()

    // Intent Explicito
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    lateinit var lista: Tratamiento
    lateinit var actividad: Medicamento

    // Campos
    lateinit var txtTitulo: EditText
    lateinit var txtDescripcion: EditText
    lateinit var spinnerPrioridad: Spinner
    lateinit var txtFechaVencimiento: EditText
    lateinit var spinnerEtiqueta: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualizar_actividad)

        // Recibir intent
        actividad = intent.getParcelableExtra("actividad")!!
        lista = intent.getParcelableExtra("lista")!!
        this.setTitle(lista.titulo)

        // Campos
        txtTitulo = findViewById(R.id.et_tituloActualizar)
        val txtFechaCreacion = findViewById<TextView>(R.id.tv_fechaVisualizar)
        val txtUsuarioCreador = findViewById<TextView>(R.id.tv_usuarioVisualizar)
        txtDescripcion = findViewById(R.id.et_descripcionVisualizar)
        spinnerPrioridad = findViewById(R.id.sp_prioridadVisualizar)
        txtFechaVencimiento = findViewById(R.id.et_fechaVisualizar)
        spinnerEtiqueta = findViewById(R.id.sp_etiquetaVisualizar)

        // Rellenar los campos
        txtTitulo.setText(actividad.nombre)
        val format = SimpleDateFormat("dd/MM/yyyy")
        txtFechaCreacion.setText(format.format(actividad.fechaCreacion))
        txtUsuarioCreador.setText(actividad.propietario.toString())
        txtDescripcion.setText(actividad.indicaciones)
        txtFechaVencimiento.setText(format.format(actividad.fechaFin))

        obtenerPrioridades()
        spinnerPrioridad.setSelection(actividad.recordatorioDia - 1)

        obtenerEtiquetas(lista.observaciones!!)
        spinnerEtiqueta.setSelection(listaEtiquetas.indexOf(actividad.observaciones.toString()))

        spinnerEtiqueta.setOnItemSelectedListener( object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == listaEtiquetas.size - 1) {
                    mostrarDialogoNuevaEtiqueta(lista)
                    obtenerEtiquetas(lista.observaciones!!)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

    }

    fun obtenerPrioridades() {
        for (i in Medicamento.MAX_PRIORIDAD .. Medicamento.MIN_PRIORIDAD) {
            listaPrioridades.add(i.toString())
        }
        val adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item, listaPrioridades
        )
        spinnerPrioridad.adapter = adapter
    }

    fun obtenerEtiquetas(etiqueta: ArrayList<Observaciones>){
        listaEtiquetas.clear()
        listaEtiquetas.add(Observaciones.SIN_ETIQUETA)
        for (nombre in etiqueta){
            listaEtiquetas.add(nombre.toString())
        }
        listaEtiquetas.add(Observaciones.AGREGAR_ETIQUETA)

        val adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item, listaEtiquetas
        )
        spinnerEtiqueta.adapter = adapter
    }

    fun actualizarCambios(listaID: String, actividadID: String) {
        val formatoFecha = SimpleDateFormat("dd/M/yyyy")
        val fechaVencimiento = formatoFecha.parse(txtFechaVencimiento.text.toString())

        db.runTransaction { transaction ->
            transaction.update(
                db.collection("Tratamiento/${listaID}/Medicamento").document(actividadID),
                mapOf(
                    "titulo" to txtTitulo.text.toString(),
                    "descripcion" to txtDescripcion.text.toString(),
                    "prioridad" to spinnerPrioridad.selectedItem.toString().toInt(),
                    "fecha_vencimiento" to Timestamp(fechaVencimiento),
                    "etiqueta" to spinnerEtiqueta.selectedItem.toString()
                )
            )
        }
            // Volver a la Tratamiento de actividades correspondiente
            .addOnSuccessListener {
                val intentExplicito = Intent(this, verTratamientos::class.java)
                intentExplicito.putExtra("listaSeleccionada", lista)
                startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
            }
    }

    override fun onBackPressed() {
        actualizarCambios(lista.id!!, actividad.id!!)
    }

    fun mostrarDialogoNuevaEtiqueta(lista: Tratamiento?) {
        val builder = AlertDialog.Builder(this)
        val txtEtiqueta = EditText(this)
        txtEtiqueta.hint = "Nueva etiqueta"
        txtEtiqueta.background = resources.getDrawable(R.drawable.et_borders)
        val scale = resources.displayMetrics.density
        txtEtiqueta.width = (10 * scale + 0.5f).toInt()
        txtEtiqueta.height = (48 * scale + 0.5f).toInt()
        txtEtiqueta.setPadding(100, txtEtiqueta.paddingTop, 100, txtEtiqueta.paddingBottom)
        txtEtiqueta.setEms(10)

        builder.setTitle("Crear Observaciones")
        builder.setMessage("Nombre:")
        builder.setView(txtEtiqueta)
        builder
            .setPositiveButton("Aceptar", null)
            .setNegativeButton("Cancelar", null)

        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()

        // Aceptar
        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            .setOnClickListener {
                when {
                    // Campo vacio
                    txtEtiqueta.text.isEmpty() -> {
                        val msg = Toast.makeText(this, "Llene el campo de observación", Toast.LENGTH_SHORT)
                        msg.show()
                    }
                    // Observaciones que ya esta en la lista
                    lista?.observaciones!!.map { it.nombre }.contains(txtEtiqueta.text.toString()) -> {
                        val usuarioExistenteMsg = Toast.makeText(
                            this,
                            "La observación ya existe",
                            Toast.LENGTH_SHORT
                        )
                        usuarioExistenteMsg.show()
                    }
                    // Se registra la etiqueta
                    else -> {
                        agregarNuevaEtiqueta(txtEtiqueta.text.toString(),lista)
                        dialog.dismiss()
                    }
                }
            }
        // Cancelar
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            .setOnClickListener {
                spinnerEtiqueta.setSelection(listaEtiquetas.indexOf(actividad.observaciones.toString()))
                dialog.dismiss()
            }
    }

    fun agregarNuevaEtiqueta(etiqueta: String, lista: Tratamiento?) {
        val posicion = listaEtiquetas.size - 1
        listaEtiquetas.add(posicion, etiqueta)
        lista?.observaciones?.add(Observaciones(etiqueta))

        spinnerEtiqueta.setSelection(posicion)

        val listaDoc = db.collection("Tratamiento").document(lista?.id.toString())

        db.runTransaction{ transaction ->
            transaction.update(
                listaDoc,
                "etiquetas", lista?.observaciones?.map{it.nombre}!!.toMutableList()
            )
        }
    }

}