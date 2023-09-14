package com.example.app_proyecto2.entities

import android.os.Parcel
import android.os.Parcelable

class Tratamiento(
    val id: String?,
    val titulo: String?,
    val usuarios: ArrayList<Usuario>?,
    val correoUsuario: String?,
    val observaciones: ArrayList<Observaciones>?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        arrayListOf<Usuario>().apply {
            parcel.readList(this, Usuario.javaClass.classLoader)
        },
        parcel.readString(),
        arrayListOf<Observaciones>().apply {
            parcel.readList(this, Observaciones.javaClass.classLoader)
        }
    ) {
    }

    override fun toString(): String {
        return titulo!!
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(titulo)
        parcel.writeList(usuarios)
        parcel.writeString(correoUsuario)
        parcel.writeList(observaciones)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tratamiento> {
        override fun createFromParcel(parcel: Parcel): Tratamiento {
            return Tratamiento(parcel)
        }

        override fun newArray(size: Int): Array<Tratamiento?> {
            return arrayOfNulls(size)
        }
    }
}