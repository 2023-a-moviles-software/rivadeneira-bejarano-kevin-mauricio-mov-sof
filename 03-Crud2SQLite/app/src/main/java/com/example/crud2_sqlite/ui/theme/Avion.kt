class Avion {
    val id: Int;
    var aerolinea: String;
    var modelo: String;
    var capacidad: Int;
    var enServicio: Boolean;

    constructor(
        id: Int,
        aerolinea: String,
        modelo: String,
        capacidad: Int,
        enServicio: Boolean)
    {
        this.id = id
        this.aerolinea = aerolinea
        this.modelo = modelo
        this.capacidad = capacidad
        this.enServicio = enServicio
    }


}