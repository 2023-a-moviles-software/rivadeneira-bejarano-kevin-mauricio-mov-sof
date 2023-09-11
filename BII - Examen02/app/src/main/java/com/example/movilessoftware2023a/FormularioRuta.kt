package com.example.movilessoftware2023a

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("NewApi")
class FormularioRuta : AppCompatActivity() {

    var accion = Accion.Registrar
    var avion: Avion?= null
    lateinit var ruta: Ruta
    val movimiento = CambioActividad(this)
    lateinit var generadorSnackbar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_ruta)

        movimiento.callback = {
            intent ->
            intent.putExtra("idAvion", this.intent.getIntExtra("idAvion", -1))
        }

        generadorSnackbar = Snackbar(findViewById(R.id.te_modo_ruta))
        accion = intent.getSerializableExtra("modo") as Accion
        val modoText = findViewById<TextView>(R.id.te_modo_ruta)
        modoText.text = accion.tipo
        val idAvion = intent.getIntExtra("idAvion", -1)
        val idRuta = intent.getIntExtra("idRuta", -1)
        val avion = BaseDeDatos.buscarAvion(idAvion)
        val txtAvion = findViewById<TextView>(R.id.tv_avion_ruta)


        avion?.let {
            txtAvion.text = it.modelo
            it.rutas.find {
                    ruta ->  ruta.id == idRuta
            }?.let {
                    self -> ruta = self
            }
        }

        if (accion == Accion.Editar) {
            cargarDatosRuta(ruta)
        }

        val botonGuardar = findViewById<Button>(R.id.btn_guardar_ruta)
        botonGuardar.setOnClickListener {
            accionGuardar()
        }
    }

    override fun onRestart() {
        super.onRestart()
    }

    fun cargarDatosRuta(ruta: Ruta) {
        val origen = findViewById<EditText>(R.id.tv_origen_ruta)
        val destino = findViewById<EditText>(R.id.tv_destino_ruta)
        val fecha = findViewById<EditText>(R.id.tv_fecha_ruta)
        val hora = findViewById<EditText>(R.id.tv_hora_ruta)
        val duracion = findViewById<EditText>(R.id.tv_duracion_ruta)
        origen.setText(ruta.origen)
        destino.setText(ruta.destino)
        fecha.setText(ManejoFechas.imprimirFecha(ruta.fecha))
        hora.setText(ruta.hora)
        duracion.setText(ruta.duracion.toString())
    }

    fun accionGuardar() {
        val origen = findViewById<EditText>(R.id.tv_origen_ruta)
        val destino = findViewById<EditText>(R.id.tv_destino_ruta)
        val fecha = findViewById<EditText>(R.id.tv_fecha_ruta)
        val hora = findViewById<EditText>(R.id.tv_hora_ruta)
        val duracion = findViewById<EditText>(R.id.tv_duracion_ruta)
        if (accion == Accion.Registrar) {
            val idAvion = intent.getIntExtra("idAvion", -1)
            //val avion = BaseDeDatos.buscarAvion(idAvion)
            avion?.let {
                val rutaCreada = Ruta(
                    origen = origen.text.toString(),
                    destino = destino.text.toString(),
                    fecha = ManejoFechas.castFecha(fecha.text.toString()),
                    hora = hora.text.toString(),
                    avion = it,
                    duracion = duracion.text.toString().toInt(),
                    id = it.rutas.size + 1
                )
                crear
                generadorSnackbar.mostrar("Ruta creada con éxito")
                finish()
            }
        }else if (accion == Accion.Editar) {
            ruta.origen = origen.text.toString()
            ruta.destino = destino.text.toString()
            ruta.fecha = ManejoFechas.castFecha(fecha.text.toString())
            ruta.hora = hora.text.toString()
            ruta.duracion = duracion.text.toString().toInt()
            generadorSnackbar.mostrar("Ruta actualizada con éxito")
            finish()
        }
    }
    fun crearVideojuego(ruta: Videojuego) {
        BaseDatos.crearVideojuego(videojuego) {
            finish()
        }
    }

    fun actualizarVideojuego(videojuego: Videojuego) {
        BaseDatos.actualizarVideojuego(videojuego) {
            when(it) {
                Variables.EXITO -> {
                    finish()
                }

                Variables.FALLO -> {
                    generadorSnackbar.mostrar("Error al actualizar el videojuego")
                }
            }
        }
    }

}