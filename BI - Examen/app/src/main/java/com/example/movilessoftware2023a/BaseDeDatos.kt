package com.example.movilessoftware2023a

import android.annotation.SuppressLint
import java.time.LocalDate

class BaseDeDatos {
    @SuppressLint("NewApi")
    companion object {
        val aviones = ArrayList<Avion>()
        private var contador = 0

        init {
            aviones.add(
                Avion(
                    id = 1,
                    modelo = "B737",
                    capacidadPasajeros = 340,
                    numSerie = 1865465189,
                    estado = "En vuelo",
                    aerolinea = "Avianca"
                )
            )

            aviones.add(
                Avion(
                    id = 2,
                    modelo = "B7900",
                    capacidadPasajeros = 420,
                    numSerie = 188565129,
                    estado = "En mantenimiento",
                    aerolinea = "Latam"
                )
            )

            aviones.add(
                Avion(
                    id = 3,
                    modelo = "A380",
                    capacidadPasajeros = 150,
                    numSerie = 1886513289,
                    estado = "En tierra",
                    aerolinea = "Equair"
                )
            )

            Ruta(
                    id = 1,
                    origen = "Guayaquil",
                    destino = "Cuenca",
                    avion = aviones[0],
                    fecha = LocalDate.of(1985,9,13),
                    hora = "14:00",
                    duracion = 50
            )

            Ruta(
                id = 2,
                origen = "Quito",
                destino = "Cuenca",
                avion = aviones[0],
                fecha = LocalDate.of(1985,9,13),
                hora = "14:00",
                duracion = 50
            )

            Ruta(
                id = 3,
                origen = "Guayaquil",
                destino = "Manta",
                avion = aviones[2],
                fecha = LocalDate.of(1985,9,13),
                hora = "14:00",
                duracion = 50
            )

            Ruta(
                id = 1,
                origen = "EEUU",
                destino = "Cuenca",
                avion = aviones[2],
                fecha = LocalDate.of(1985,9,13),
                hora = "14:00",
                duracion = 50
            )

            contador = aviones.size

        }

        fun buscarAvion(id: Int): Avion? {
            return aviones.find { it.id == id }
        }

        fun eliminarAvion(id: Int): Boolean {
            val avion = buscarAvion(id) ?: return false
            return aviones.remove(avion)
        }

        fun guardarAvion(avion: Avion) {
            aviones.add(avion)
        }

        fun crearAvion(modelo: String, capacidadPasajeros: Int, numSerie: Int, estado: String, aerolinea: String) {
            aviones.add(
                Avion(
                    modelo = modelo,
                    capacidadPasajeros = capacidadPasajeros,
                    numSerie = numSerie,
                    estado = estado,
                    aerolinea = aerolinea,
                    id = contador + 1
                )
            )
            contador += 1
        }

        fun actualizarAvion(modelo: String, capacidadPasajeros: Int, numSerie: Int, estado: String, aerolinea: String, id: Int) {
            val avion = buscarAvion(id)
            avion?.modelo = modelo
            avion?.capacidadPasajeros = capacidadPasajeros
            avion?.numSerie = numSerie
            avion?.estado = estado
            avion?.aerolinea = aerolinea
        }
    }


}