import java.io.File
import java.io.PrintWriter
import java.util.Date
import java.text.SimpleDateFormat

    data class Avion(val id: Int, var nombre: String, var modelo: String, var capacidad: Int, var enServicio: Boolean)
    data class Ruta(val id: Int, var origen: String, var destino: String, var duracion: Double, var fecha: Date, var avionId: Int)
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    fun main() {
        //Crea una variable de tipo archivo
        val avionesFile = File("aviones.txt")
        val rutasFile = File("rutas.txt")

        //Verifica su existencia, caso contrario lo creo
        if (!avionesFile.exists() || !rutasFile.exists()) {
            avionesFile.createNewFile()
            rutasFile.createNewFile()
            println("Archivo creado exitosamente.")
        } else {
            println("Archivo existente")
        }

        //Genera un array de tipo Avión y Ruta
        var aviones = mutableListOf<Avion>()
        var rutas = mutableListOf<Ruta>()

        //Carga datos quemados en el archivo en caso de existir
        cargarAviones(avionesFile, aviones)
        cargarRutas(rutasFile, rutas)

        var opcion: Int
        do {
            mostrarMenu()
            opcion = readLine()?.toIntOrNull() ?: 0
            when (opcion) {
                1 -> crearAvion(aviones, avionesFile)
                2 -> crearRuta(rutas, aviones, rutasFile)
                3 -> mostrarAviones(aviones)
                4 -> mostrarRutas(rutas, aviones)
                5 -> actualizarAvion(aviones, avionesFile)
                6 -> actualizarRuta(rutas, aviones, rutasFile)
                7 -> eliminarAvion(aviones, rutas, avionesFile, rutasFile)
                8 -> eliminarRuta(rutas)
                else -> println("Opción inválida. Ingrese una opción válida.")
            }
        } while (opcion != 9)
    }

    //Control para pre-cargar información de aviones desde el archivo txt
    fun cargarAviones(file: File, aviones: MutableList<Avion>) {
        if (file.exists()) {
            file.readLines().forEach { line ->
                val datos = line.split(",") //tokenizo los valores entre el delimitador
                val avion = Avion(
                    datos[0].toInt(),
                    datos[1],
                    datos[2],
                    datos[3].toInt(),
                    datos[4].toBoolean()
                )
                aviones.add(avion) //cargo en memoria
            }
        }
    }

    //Control para pre-cargar información de rutas desde el archivo txt
    fun cargarRutas(file: File, rutas: MutableList<Ruta>) {
        if (file.exists()) {
            file.readLines().forEach { line ->
                val datos = line.split(",") //tokenizo los valores entre el delimitador
                val ruta = Ruta(
                    datos[0].toInt(),
                    datos[1],
                    datos[2],
                    datos[3].toDouble(),
                    dateFormat.parse(datos[4]),
                    datos[5].toInt()
                )
                rutas.add(ruta) //cargo en memoria
            }
        }
    }

    //CRUD 1: CREATE
    //Función para crear un avión y guardarlo en el archivo txt
    fun crearAvion(aviones: MutableList<Avion>, avionesFile:File) {
        println("******** Crear Avión ********")
        print("Ingresa el ID del avión: ")
        val id = readLine()?.toIntOrNull()

        print("Ingresa el nombre del avión: ")
        val nombre = readLine()

        print("Ingresa el modelo del avión: ")
        val modelo = readLine()

        print("Ingresa la capacidad del avión: ")
        val capacidad = readLine()?.toIntOrNull()

        print("¿El avión se encuentra en servicio?: ")
        val enServicio = readLine().toBoolean()

        if (id != null && nombre != null && modelo != null && capacidad != null && enServicio != null) {
            val avion = Avion(id, nombre, modelo, capacidad, enServicio )
            aviones.add(avion)
            println("Avión creado exitosamente.")
            guardarAviones(avionesFile, aviones) //Carga de memoria al archivo
        } else {
            println("Error al crear el avión. Ingrese valores válidos.")
        }
    }

    //Función para crear una ruta con un avión existente y guardarlo en el archivo txt
    fun crearRuta(rutas: MutableList<Ruta>, aviones: MutableList<Avion>, rutasFile: File) {
        println("******** Crear Ruta ********")
        print("Ingresa el ID de la ruta: ")
        val id = readLine()?.toIntOrNull()

        print("Ingresa el origen de la ruta: ")
        val origen = readLine()

        print("Ingresa el destino de la ruta: ")
        val destino = readLine()

        print("Ingresa la duración de la ruta en minutos: ")
        val duracion = readLine()?.toDoubleOrNull()

        print("Ingresa la fecha de la ruta en formato (dd/mm/aaaa): ")
        val fecha = dateFormat.parse(readLine())


        print("Ingresa el ID del avión asociado a la ruta: ")
        val avionId = readLine()?.toIntOrNull()

        if (id != null && origen != null && destino != null && duracion != null && fecha != null && avionId != null) {
            val avionExistente = aviones.find { it.id == avionId } //Verifica que el avión esté registrado
            if (avionExistente != null) {
                val ruta = Ruta(id, origen, destino, duracion,fecha, avionId)
                rutas.add(ruta) //Añade al listado de rutas
                println("Ruta creada exitosamente.")
                guardarRutas(rutasFile, rutas) //Carga de memoria al archivo
            } else {
                println("No existe un avión con el ID especificado.")
            }
        } else {
            println("Error al crear la ruta. Ingrese valores válidos.")
        }
    }

    //CRUD 2: READ
    //Función para mostrar las rutas creadas o cargadas
    fun mostrarRutas(rutas: MutableList<Ruta>, aviones: MutableList<Avion>) {
        println("******** Rutas ********")
        if (rutas.isEmpty()) { //Verifico que el array no esté vacío
            println("No hay rutas disponibles.")
        } else {
            for (ruta in rutas) {
                val avion = aviones.find { it.id == ruta.avionId }
                print(" | ID: ${ruta.id}".format())
                print(" | Origen: ${ruta.origen}".format())
                print(" | Destino: ${ruta.destino}".format())
                print(" | Duración: ${ruta.duracion} minutos".format())
                print(" | Fecha: ${ruta.fecha}".format())
                println(" | Avión: ${avion?.nombre} (ID: ${avion?.id})".format())

            }
            println("************************")
        }
    }

    //Función para mostrar los aviones creados o cargados
    fun mostrarAviones(aviones: List<Avion>) {
        if (aviones.isEmpty()) {
            println("No hay aviones registrados.")
        } else {
            println("******** Lista de Aviones ********")
            aviones.forEach { avion ->
                print(" | ID: ${avion.id}".format())
                print(" | Nombre: ${avion.nombre}".format())
                print(" | Modelo: ${avion.modelo}".format())
                print(" | Capacidad: ${avion.capacidad}".format())
                println(" | En Servicio: ${avion.enServicio}".format())

            }
            println("************************")
        }
    }

    //CRUD 3: UPDATE
    //Función para actualizar campos de un avión a partir de su ID
    fun actualizarAvion(aviones: MutableList<Avion>, avionesFile: File) {
        println("******** Actualizar Avión ********")
        println("Ingrese el ID del avión a actualizar:")
        val id = readLine()?.toInt()

        //Solicita los datos a actualizar, en caso de nulo lo mantiene
        val avion = aviones.find { it.id == id }
        if (avion != null) {
            println("Ingrese el nuevo nombre del avión:")
            val nombre = readLine()

            println("Ingrese el nuevo modelo del avión:")
            val modelo = readLine()

            println("Ingrese la nueva capacidad del avión:")
            val capacidad = readLine()?.toInt()

            println("Ingrese la nueva aerolínea del avión:")
            val enServicio = readLine().toBoolean()

            avion.nombre = nombre.toString()
            avion.modelo = modelo.toString()
            if (capacidad != null) {
                avion.capacidad = capacidad
            }
            avion.enServicio = enServicio

            println("Avión actualizado exitosamente.")
            guardarAviones(avionesFile, aviones)
        } else {
            println("No se encontró ningún avión con el ID proporcionado.")
        }
    }

    //Función para actualizar campos de un avión a partir de su ID
    fun actualizarRuta(rutas: MutableList<Ruta>, aviones: List<Avion>, rutasFile: File) {
        println("******** Actualizar Ruta ********")
        println("Ingrese el ID de la ruta a actualizar:")
        val id = readLine()?.toInt()

        //Solicita los datos a actualizar, en caso de nulo lo mantiene
        val ruta = rutas.find { it.id == id }
        if (ruta != null) {
            println("Ingrese el nuevo origen de la ruta:")
            val origen = readLine()

            println("Ingrese el nuevo destino de la ruta:")
            val destino = readLine()

            println("Ingrese la nueva duración de la ruta:")
            val duracion = readLine()?.toIntOrNull() ?: ruta.duracion

            println("Ingrese la nueva fecha de la ruta:")
            val fecha = dateFormat.parse(readLine()) ?: ruta.fecha

            println("Ingrese el nuevo ID del avión para la ruta:")
            val avionId = readLine()?.toIntOrNull() ?: ruta.avionId

            val avionExistente = aviones.find { it.id == avionId }
            if (avionId != null && avionExistente != null) {
                ruta.origen = origen.toString()
                ruta.destino = destino.toString()
                ruta.duracion = duracion.toDouble()
                ruta.fecha = fecha
                ruta.avionId = avionId

                guardarRutas(rutasFile, rutas)
                println("Ruta actualizada exitosamente.")
            } else {
                println("No se pudo actualizar la ruta. El ID del avión no es válido.")
            }
        } else {
            println("No se encontró ninguna ruta con el ID buscado.")
        }
    }

    //CRUD 4: DELETE
    //Función para eliminar aviones a partir de su id, además de sus rutas asociadas
    fun eliminarAvion(aviones: MutableList<Avion>, rutas: MutableList<Ruta>, avionesFile: File, rutasFile: File){
        println("******** Eliminar Avión ********")
        println("Ingrese el ID del avión a eliminar:")
        val id = readLine()?.toInt()
        val avion=aviones.find { it.id==id }

        if (avion != null) {
            // Eliminar todas las rutas asociadas al avión
            val rutasEliminadas = rutas.filter { it.avionId == avion.id }
            rutas.removeAll(rutasEliminadas)

            // Eliminar el avión
            aviones.remove(avion)
            guardarAviones(avionesFile, aviones)
            guardarRutas(rutasFile, rutas)
            println("Avión eliminado exitosamente.")
        } else {
            println("No se encontró ningún avión con el ID buscado.")
        }
    }

    //Función para eliminar rutas a partir de su id
    fun eliminarRuta(rutas: MutableList<Ruta>) {
        println("******** Eliminar Ruta ********")
        println("Ingrese el ID de la ruta a eliminar:")
        val id = readLine()?.toInt()
        //Se localiza rutas coincidentes con el ID
        val ruta = rutas.find { it.id == id }
        if (ruta != null) {
            rutas.remove(ruta)
            println("Ruta eliminada exitosamente.")
        } else {
            println("No se encontró ninguna ruta con el ID buscado.")
        }
    }

    //FUNCIONES COMPLEMENTARIAS FUERA DEL CRUD
    /*Función para desplegar el menú de selección con las opciones CRUD*/
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

    //Función para guardar los datos en memoria hacia el archivo txt
    fun guardarAviones(file: File, aviones: List<Avion>) {
        try {
            PrintWriter(file).use { writer ->
                aviones.forEach { avion -> //Extrae datos del array y los escribe en el fichero
                    writer.println("${avion.id},${avion.nombre},${avion.modelo},${avion.capacidad},${avion.enServicio}")
                }
            }
            println("Aviones guardados exitosamente en el archivo.")
        } catch (e: Exception) {
            println("Error al guardar los aviones en el archivo: ${e.message}")
        }
    }

    //Función para guardar los datos en memoria hacia el archivo txt
    fun guardarRutas(file: File, rutas: List<Ruta>) {
        try {

            PrintWriter(file).use { writer ->
                rutas.forEach { ruta -> //Extrae datos del array y los escribe en el fichero
                    val fechaStr = dateFormat.format(ruta.fecha)
                    writer.println("${ruta.id},${ruta.origen},${ruta.destino},${ruta.duracion},${fechaStr},${ruta.avionId}")
                }
            }
            println("Rutas guardadas exitosamente en el archivo.")
        } catch (e: Exception) {
            println("Error al guardar las rutas en el archivo: ${e.message}")
        }
    }

    fun String.format(length: Int = 15): String {
        val padding = (length - this.length) / 2
        return this.padStart(padding + this.length).padEnd(length)
    }

