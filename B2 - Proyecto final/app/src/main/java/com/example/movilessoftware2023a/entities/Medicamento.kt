package com.example.app_proyecto2.entities

import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.*

class Medicamento(
    val id: String?,
    val nombre: String?,
    val indicaciones: String?,
    val fechaCreacion: Date?,
    val fechaFin: Date?,
    val recordatorioDia: Int,
    val observaciones: Observaciones?,
    val propietario: Usuario?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Date::class.java.classLoader) as? Date,
        parcel.readValue(Date::class.java.classLoader) as? Date,
        parcel.readInt(),
        parcel.readParcelable(Observaciones::class.java.classLoader),
        parcel.readParcelable(Usuario::class.java.classLoader)
    ) {
    }

    override fun toString(): String {
        val format = SimpleDateFormat("dd/MM/yyyy")
        return "$nombre\nPara el ${format.format(fechaFin)}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(nombre)
        parcel.writeString(indicaciones)
        parcel.writeValue(fechaCreacion)
        parcel.writeValue(fechaFin)
        parcel.writeInt(recordatorioDia)
        parcel.writeParcelable(observaciones, flags)
        parcel.writeParcelable(propietario, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Medicamento> {
        // Prioridades
        const val MAX_PRIORIDAD = 1
        const val MIN_PRIORIDAD = 5

        // Filtros
        const val HINT = "Filtro"
        const val FECHA_ASCENDENTE = "Fecha límite (Ascendente)"
        const val FECHA_DESCENDENTE = "Fecha límite (Descendente)"
        const val FILTRO_PRIORIDAD = "Por recordatorio"
        //const val FILTRO_ETIQUETA = "Por etiqueta"
        val OPCIONES_FILTRO = arrayListOf(
            HINT,
            FECHA_ASCENDENTE,
            FECHA_DESCENDENTE,
            FILTRO_PRIORIDAD,
            //FILTRO_ETIQUETA,
        )

        override fun createFromParcel(parcel: Parcel): Medicamento {
            return Medicamento(parcel)
        }

        override fun newArray(size: Int): Array<Medicamento?> {
            return arrayOfNulls(size)
        }
    }
}