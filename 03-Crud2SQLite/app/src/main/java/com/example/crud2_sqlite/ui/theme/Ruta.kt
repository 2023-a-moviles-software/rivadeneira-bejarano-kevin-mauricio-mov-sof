import java.sql.Date

class Ruta {
    val id: Int
    var origen: String
    var destino: String
    var duracion: Int
    var fecha: String
    var avionId: Int

    constructor(
        id: Int,
        origen: String,
        destino: String,
        duracion: Int,
        fecha: String,
        avionId: Int
    ) {
        this.id = id
        this.origen = origen
        this.destino = destino
        this.duracion = duracion
        this.fecha = fecha
        this.avionId = avionId
    }
}