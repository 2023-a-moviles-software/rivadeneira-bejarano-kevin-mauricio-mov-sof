package com.example.movilessoftware2023a

class Avion {
    var modelo: String
    var capacidadPasajeros: Int
    var numSerie: Int
    var estado: String
    var aerolinea: String
    val rutas: ArrayList<Ruta>
    val id: Int?

    constructor(modelo: String, capacidadPasajeros: Int, numSerie: Int, estado: String, aerolinea: String, rutas: ArrayList<Ruta> = ArrayList(), id: Int? = null) {
        this.modelo = modelo
        this.capacidadPasajeros = capacidadPasajeros
        this.numSerie = numSerie
        this.estado = estado
        this.aerolinea = aerolinea
        this.id = id
        this.rutas = rutas
    }

    override fun toString(): String {
        return "${this.modelo} (${this.aerolinea})" +
                "\n  > Capacidad: ${this.capacidadPasajeros} pasajeros" +
                "\n  > N° Serie: ${this.numSerie}" +
                "\n  > Estado: ${this.estado}\n"
    }
}