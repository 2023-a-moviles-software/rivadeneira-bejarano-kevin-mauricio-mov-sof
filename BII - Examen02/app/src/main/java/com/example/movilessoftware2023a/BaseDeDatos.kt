package com.example.movilessoftware2023a

import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BaseDeDatos {
    @SuppressLint("NewApi")
    companion object {
        val aviones = ArrayList<Avion>()
        fun getDesarrolladoras(callback: (arrayList: ArrayList<Avion>) -> Unit) {
            val db = Firebase.firestore
            db.collection("avion")
                .get()
                .addOnSuccessListener { documents ->
                    val arrayList = ArrayList<Avion>()
                    for (document in documents) {
                        arrayList.add(parsearAvion(document))
                    }
                    callback(arrayList);
                }
        }

        fun parsearAvion(documento: QueryDocumentSnapshot): Avion {

            val avion = Avion(
                modelo = documento.data.get("modelo") as String,
                capacidadPasajeros = (documento.data.get("capacidadPasajeros") as Long).toInt(),
                numSerie = documento.data.get("numSerie") as Int,
                estado = documento.data.get("estado") as String,
                aerolinea = documento.id,
                //id = documento.data.get("esIndependiente") as Boolean
            )

            return avion
        }
        /*
        fun getVideojuegosPorDesarrolladora(
            desarrolladora: Desarrolladora,
            callback: (arrayList: ArrayList<Videojuego>) -> Unit ) {
            val db = Firebase.firestore
            db.collection("videojuegos")
                .whereEqualTo("desarrolladora", desarrolladora.id!!)
                .get().addOnSuccessListener {
                        documentos ->
                    val videojuegos = ArrayList<Videojuego>()
                    for (doc in documentos) {
                        val videojuego = parsearRuta(doc)
                        videojuego.desarrolladora = desarrolladora
                        videojuegos.add(videojuego)
                    }
                    callback(videojuegos)
                }
        }

        fun crearVideojuego(
            videojuego: Videojuego,
            callback: (it: DocumentReference) -> Unit) {
            val map = videojuegoAMapa(videojuego)
            val db = Firebase.firestore
            db.collection("videojuegos")
                .add(map)
                .addOnSuccessListener {
                    callback(it)
                }

        }

        fun actualizarVideojuego(videojuego: Videojuego, callback: (it: Int) -> Unit) {
            val map = videojuegoAMapa(videojuego)
            val db = Firebase.firestore
            db.collection("videojuegos")
                .document(videojuego.id!!)
                .set(map)
                .addOnSuccessListener {
                    callback(Variables.EXITO)
                }
                .addOnFailureListener {
                    callback(Variables.FALLO)
                }

        }*/
/*
        fun parsearRuta(documento: QueryDocumentSnapshot): Ruta {

            val ruta = Ruta(
                documento.data.get("nombre") as String?,
                LocalDate.parse(
                    (documento.data.get("fechaLanzamiento") as String),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")
                ),
                desarrolladora = null,
                documento.data.get("calificacion") as Double,
                plataformas = Plataforma.toList(documento.get("plataformas") as ArrayList<String>)
                        as ArrayList<Plataforma>,
                generos = Genero.toList(documento.get("generos") as ArrayList<String>)
                        as ArrayList<Genero>,
                id = documento.id
            )

            return ruta
        }*/
        /*
        fun videojuegoAMapa(videojuego: Videojuego): Map<String, Any?> {
            return  mapOf(
                "nombre" to videojuego.nombre,
                "fechaLanzamiento" to videojuego.fechaLanzamiento!!
                    .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                "calificacion" to videojuego.calificacion,
                "plataformas" to videojuego.plataformas!!.map { it.id },
                "generos" to videojuego.generos!!.map { it.key },
                "desarrolladora" to videojuego.desarrolladora!!.id!!
            )
        }

        fun eliminarVideojuego(id: String, _callback: (it: Int) -> Unit) {
            val db = Firebase.firestore
            db.collection("videojuegos")
                .document(id)
                .delete()
                .addOnSuccessListener {
                    _callback(Variables.EXITO)
                }
                .addOnFailureListener {
                    _callback(Variables.FALLO)
                }
        }*/

    }
}