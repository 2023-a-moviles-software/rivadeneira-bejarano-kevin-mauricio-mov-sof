package com.example.app_proyecto2.entities

import android.os.Parcel
import android.os.Parcelable

class Observaciones(
    val nombre: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return nombre!!
    }

    override fun equals(other: Any?): Boolean {
        if (other is Observaciones)
            return this.nombre.equals(other.nombre)
        return false
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Observaciones> {

        const val SIN_ETIQUETA = "Sin observación"
        const val AGREGAR_ETIQUETA = "+ Añadir observación"
        const val MOSTRAR_TODAS = "Mostrar todas"

        override fun createFromParcel(parcel: Parcel): Observaciones {
            return Observaciones(parcel)
        }

        override fun newArray(size: Int): Array<Observaciones?> {
            return arrayOfNulls(size)
        }
    }
}