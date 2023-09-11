package com.example.movilessoftware2023a

import android.os.Build
//import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

class Avion (
    var modelo: String?,
    var capacidadPasajeros: Int?,
    var numSerie: Int?,
    var estado: String?,
    var aerolinea: String?,
    val rutas: ArrayList<Ruta> = ArrayList(),
    val id: Int? = null
): Parcelable {

    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel): this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readArrayList(Ruta::class.java.classLoader) as ArrayList<Ruta>,
        parcel.readInt()
    )

    constructor(modelo: String, capacidadPasajeros: Int, numSerie: Int, estado: String, aerolinea: String):
            this(modelo, capacidadPasajeros, numSerie, estado, aerolinea, ArrayList(), null)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(modelo)
        parcel.writeValue(capacidadPasajeros)
        parcel.writeValue(numSerie)
        parcel.writeString(estado)
        parcel.writeString(aerolinea)
        parcel.writeValue(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Avion> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Avion {
            return Avion(parcel)
        }

        override fun newArray(size: Int): Array<Avion?> {
            return arrayOfNulls(size)
        }
    }
    override fun toString(): String {
        return "${this.modelo} (${this.aerolinea})" +
                "\n  > Capacidad: ${this.capacidadPasajeros} pasajeros" +
                "\n  > N° Serie: ${this.numSerie}" +
                "\n  > Estado: ${this.estado}\n"
    }
}






