package com.example.movilessoftware2023a

import java.time.LocalDate

class Ruta {
    var origen: String
    var destino: String
    var avion: Avion?
    var fecha: LocalDate
    var hora: String
    var duracion: Int
    val id: Int?

    constructor(
        origen: String,
        destino: String,
        avion: Avion?,
        fecha: LocalDate,
        hora: String,
        duracion: Int,
        id: Int? = null
    ) {
        this.origen = origen
        this.destino = destino
        this.avion = avion
        this.fecha = fecha
        this.hora = hora
        this.duracion = duracion
        this.id = id
        this.avion?.rutas?.add(this)
    }


    override fun toString(): String {
        return "${this.origen} - ${this.destino}" +
                "\n  > Fecha: ${this.fecha}" +
                "\n  > Hora: ${this.hora}" +
                "\n  > Duración: ${this.duracion} min\n"
    }
}