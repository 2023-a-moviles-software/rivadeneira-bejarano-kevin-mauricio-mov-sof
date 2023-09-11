package com.example.movilessoftware2023a
import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDate

@Suppress("NewApi")
class Ruta (
    var origen: String?,
    var destino: String?,
    var avion: Avion?,
    var fecha: LocalDate,
    var hora: String?,
    var duracion: Int,
    var id: Int? = null
): Parcelable {
    init {
        this.avion?.rutas?.add(this)
    }

    constructor(parcel: Parcel): this(
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Avion::class.java.classLoader),
        LocalDate.parse(parcel.readString()),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    )


    constructor(
        origen: String?,
        destino: String?,
        avion: Avion?,
        fecha: LocalDate?,
        hora: String?,
        duracion: Int
    ) : this(
        origen,
        destino,
        avion,
        fecha ?: LocalDate.now(), // Valor predeterminado para fecha si es nulo
        hora,
        duracion,
        null
    )



    override fun toString(): String {
        return "${this.origen} - ${this.destino}" +
                "\n  > Fecha: ${this.fecha}" +
                "\n  > Hora: ${this.hora}" +
                "\n  > Duración: ${this.duracion} min\n"
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeString(origen)
        parcel.writeString(destino)
        parcel.writeParcelable(avion, 0)
        parcel.writeString(fecha.toString())
        parcel.writeString(hora)
        parcel.writeInt(duracion)
        //parcel.writeInt(id)
    }

    companion object CREATOR : Parcelable.Creator<Ruta> {
        override fun createFromParcel(parcel: Parcel): Ruta {
            return Ruta(parcel)
        }

        override fun newArray(size: Int): Array<Ruta?> {
            return arrayOfNulls(size)
        }




    }
}