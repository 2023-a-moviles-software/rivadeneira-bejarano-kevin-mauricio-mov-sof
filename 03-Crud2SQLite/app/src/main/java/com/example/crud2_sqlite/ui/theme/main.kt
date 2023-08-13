package com.example.crud2_sqlite.ui.theme

import Avion
import BD_SQlite
import Ruta

    fun detalleAvion(a:Avion) {
        print(" | ID de avión: ${a.id}".format())
        print(" | Aerolinea: ${a.aerolinea}".format())
        print(" | Modelo: ${a.modelo}".format())
        print(" | Capacidad: ${a.capacidad}".format())
        println(" | En Servicio: ${a.enServicio}".format())
    }

    fun detalleRuta(r:Ruta, a:Avion) {
        print(" | ID de ruta: ${r.id}".format())
        print(" | Origen: ${r.origen}".format())
        print(" | Destino: ${r.destino}".format())
        print(" | Duración: ${r.duracion} minutos".format())
        print(" | Fecha: ${r.fecha}".format())
        println(" | Avión: ${a?.aerolinea} (ID: ${a?.id})".format())
    }

    fun mostrarMenu() {
        println("------ Menú ------")
        println("1. Crear Avión")
        println("2. Crear Ruta")
        println("3. Mostrar Aviones")
        println("4. Mostrar Rutas")
        println("5. Actualizar Avión")
        println("6. Actualizar Ruta")
        println("7. Eliminar Avión")
        println("8. Eliminar Ruta")
        println("9. Salir")
        print("Elige una opción: ")
    }

    fun crearAvion(base:BD_SQlite){
            print("ID del avión: ")
            var id_avion: Int = readln().toInt();
            print("Aerolínea: ")
            var aerolinea: String = readln();
            print("Modelo: ")
            var modelo: String = readln();
            print("Capacidad: ")
            var capacidad: Int = readln().toInt();
            print("En Servicio?(true/false) ")
            var enServicio: Boolean = readln().toBoolean();

            var resultado = base.crearAvion(id_avion,aerolinea,modelo,capacidad,enServicio);
            if (resultado){
                println("Avión insertado con éxito")
            }else{
                println("Error al insertar nuevo Avión")
            }
        }

    fun crearRuta(base:BD_SQlite){
        print("ID de la ruta: ")
        var id_ruta: Int = readln().toInt();
        print("Lugar de origen: ")
        var origen: String = readln();
        print("Lugar de destino: ")
        var destino: String = readln();
        print("Duración del viaje: ")
        var duracion: Int = readln().toInt();
        print("Fecha: ")
        val fecha: String = readln();
        var avionesCargados = base.listarAviones()
        for (avion in avionesCargados) {
            detalleAvion(avion)
        }
        print("ID del avión: ")
        var id_avion: Int = readln().toInt();
        var resultado = base.crearRuta(id_ruta, origen, destino, duracion, fecha, id_avion)
        if (resultado){
            println("Ruta insertada con éxito")
        }else{
            println("Error al insertar nueva Ruta")
        }
    }

    fun actualizarAvion(base:BD_SQlite){
        var avionesCargados = base.listarAviones()
        for (avion in avionesCargados) {
            detalleAvion(avion)
        }
        print("Ingrese ID del avión a actualizar:")
        var id_avion: Int = readln().toInt();
        print("Actualizar Aerolínea: ")
        var aerolinea: String = readln();
        print("Actualizar Modelo: ")
        var modelo: String = readln();
        print("Actualizar Capacidad: ")
        var capacidad: Int = readln().toInt();
        print("Actualizar En Servicio?(true/false) ")
        var enServicio: Boolean = readln().toBoolean();
        var resultado = base.actualizarAvion(id_avion,aerolinea,modelo,capacidad,enServicio);
        if (resultado){
            println("Avión actualizado con éxito")
        }else{
            println("Error al actualizar Avión")
        }
    }

    fun actualizarRuta(base:BD_SQlite){
        var rutasCargadas = base.listarRutas()
        for (ruta in rutasCargadas) {
            var avion: Avion? =base.buscarAvionporID(ruta.avionId);
            if (avion != null) {
                detalleRuta(ruta, avion )
            }
        }
        print("Ingrese ID de la ruta a actualizar: ")
        var id_ruta: Int = readln().toInt();
        print("Actualizar Lugar de origen: ")
        var origen: String = readln();
        print("Actualizar Lugar de destino: ")
        var destino: String = readln();
        print("Actualizar Duración del viaje: ")
        var duracion: Int = readln().toInt();
        print("Actualizar Fecha: ")
        val fecha: String = readln();
        var avionesCargados = base.listarAviones()
        for (avion in avionesCargados) {
            detalleAvion(avion)
        }
        print("Actualizar ID del avión: ")
        var id_avion: Int = readln().toInt();
        var resultado = base.actualizarRuta(id_ruta,id_avion, origen, destino, duracion, fecha )
        if (resultado){
            println("Ruta actualizada con éxito")
        }else{
            println("Error al actualizar Ruta")
        }
    }

    fun eliminarRuta(base: BD_SQlite){
        var rutasCargadas = base.listarRutas()
        for (ruta in rutasCargadas) {
            var avion: Avion? =base.buscarAvionporID(ruta.avionId);
            if (avion != null) {
                detalleRuta(ruta, avion )
            }
        }
        print("Ingrese ID de la ruta a eliminar: ")
        var id_ruta: Int = readln().toInt();
        var resultado = base.eliminarRutaPorID(id_ruta)
        if (resultado){
            println("Ruta eliminada con éxito")
        }else{
            println("Error al eliminar Ruta")
        }
    }

    fun eliminarAvion(base: BD_SQlite){
        var avionesCargados = base.listarAviones()
        for (avion in avionesCargados) {
            detalleAvion(avion)
        }
        print("Ingrese ID del avión a eliminar: ")
        var id_avion: Int = readln().toInt();
        var resultado = base.eliminarAvionPorID(id_avion)
        if (resultado){
            println("Avión eliminado con éxito")
        }else{
            println("Error al eliminar Avión")
        }
    }

    fun main(args: Array<String>){
        val BaseDatos = BD_SQlite()
        BaseDatos.crearTablasAvionRuta()

        var opcion: Int
        do {
            mostrarMenu()
            opcion = readLine()?.toIntOrNull() ?: 0
            when (opcion) {
                1 -> crearAvion(BaseDatos)
                2 -> crearRuta(BaseDatos)
                3 -> {
                    var avionesCargados = BaseDatos.listarAviones()
                    for (avion in avionesCargados) {
                        detalleAvion(avion)
                    }
                }
                4 -> {
                    var rutasCargadas = BaseDatos.listarRutas()
                    for (ruta in rutasCargadas) {
                        var avion: Avion? =BaseDatos.buscarAvionporID(ruta.avionId);
                        if (avion != null) {
                            detalleRuta(ruta, avion )
                        }
                    }
                }
                5 -> actualizarAvion(BaseDatos)
                6 -> actualizarRuta(BaseDatos)
                7 -> eliminarAvion(BaseDatos)
                8 -> eliminarRuta(BaseDatos)
                else -> println("Opción incorrecta")
            }
        } while (opcion != 9)


    }







