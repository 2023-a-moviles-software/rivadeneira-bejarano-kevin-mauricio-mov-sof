import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement


class BD_SQlite (){

    private val connection: Connection = DriverManager.getConnection("jdbc:sqlite:Crud2SQLite")
    private val statement: Statement = connection.createStatement()

    fun crearTablasAvionRuta() {
        val revisarTablaAvion = "SELECT name FROM sqlite_master WHERE type='table' AND name='Avion';"
        val revisarTablaRuta = "SELECT name FROM sqlite_master WHERE type='table' AND name='Ruta';"

        val resultSetAvion = statement.executeQuery(revisarTablaAvion)
        val resultSetRuta = statement.executeQuery(revisarTablaRuta)

        val existeTablaAvion = resultSetAvion.next()
        val existeTablaRuta = resultSetRuta.next()


        if (existeTablaRuta) {
            val crearTablaRUta = """
            CREATE TABLE RUTA (
                ID_RUTA         INTEGER      PRIMARY KEY AUTOINCREMENT,
                ID_AVION        INTEGER     NOT NULL,
                ORIGEN          CHAR(15),
                DESTINO          CHAR(20),
                DURACION        INTEGER,
                FECHA            CHAR(20),
                FOREIGN KEY (ID_AVION) REFERENCES AVION (ID_AVION)
            );
            UPDATE RUTA SET FECHA = strftime('%Y-%m-%d', FECHA);
        """.trimIndent()
            statement.execute(crearTablaRUta)
        }

        if (existeTablaAvion) {
            println("No existió la tabla avión")
            val crearTablaAvion = """
            CREATE TABLE AVION (
                ID_AVION         INTEGER     PRIMARY KEY NOT NULL,
                NOMBRE           CHAR(20),
                MODELO           CHAR(20),
                CAPACIDAD         INTEGER,
                ENSERVICIO         CHAR(50)
            );
        """.trimIndent()
            statement.execute(crearTablaAvion)
        }
    }

    fun borrarTablas() {
        val borrarTablaAvion = "DROP TABLE IF EXISTS Avion;"
        val borrarTablaRuta = "DROP TABLE IF EXISTS Ruta;"

        statement.execute(borrarTablaRuta)
        statement.execute(borrarTablaAvion)
    }

    fun close(){
        statement.close()
        connection.close()
    }

    // CRUD AVION
    fun crearAvion(id_avion: Int,
                   nombre: String?,
                   modelo: String?,
                   capacidad: Int?,
                   enServicio: Boolean?): Boolean{
        val insertQuery =
            """
        INSERT INTO AVION (ID_AVION, NOMBRE, MODELO, CAPACIDAD, ENSERVICIO)
        VALUES ('$id_avion', '$nombre', '$modelo', '$capacidad', '$enServicio')
         """.trimIndent()
        try{
            statement.executeUpdate(insertQuery)
            return true
        }catch(e: Exception){
            println("Error al insertar avión: ${e.toString()}")
        }
        return false
    }

    fun buscarAvionporID(id_avion: Int): Avion?{
        val query = """
            SELECT * FROM AVION 
            WHERE ID_AVION = '$id_avion'
        """.trimIndent()

        val resultSet = statement.executeQuery(query)
        try{
            if(resultSet.next()){
                val id_avion = resultSet.getInt("ID_AVION")
                val nombre = resultSet.getString("NOMBRE")
                val modelo = resultSet.getString("MODELO")
                val capacidad = resultSet.getInt("CAPACIDAD")
                val enServicio = resultSet.getBoolean("ENSERVICIO")

                return Avion(id_avion, nombre, modelo, capacidad, enServicio)
            }
        } catch(e: Exception){
            println("A ocurrido un error al consultar la tabla docente: ${e.toString()}")
        }finally {
            resultSet.close()
        }
        return null
    }

    fun eliminarAvionPorID(id_avion: Int): Boolean{
        val queryEliminarRutas = """
            DELETE FROM RUTA 
            WHERE ID_AVION = ?
        """.trimIndent()

        val queryEliminarAvion = """
            DELETE FROM AVION 
            WHERE ID_AVION = ?
        """.trimIndent()

        try{
            val eliminarRutasStatement = connection.prepareStatement(queryEliminarRutas)
            val eliminarAvionStatement = connection.prepareStatement(queryEliminarAvion)

            eliminarRutasStatement.setInt(1, id_avion)
            eliminarRutasStatement.executeUpdate()

            eliminarAvionStatement.setInt(1, id_avion)
            eliminarAvionStatement.executeUpdate()
            return true
        }catch(e:Exception){
            println("Error al Eliminar avión: ${e.toString()}")
        }
        return false
    }

    fun actualizarAvion(id_avion: Int,nombre: String, modelo: String, capacidad: Int, enServicio: Boolean): Boolean {
        val query = """
        UPDATE AVION 
        SET NOMBRE = ?, MODELO = ?, CAPACIDAD = ?, ENSERVICIO = ?
        WHERE ID_AVION = ?
    """.trimIndent()

        try {
            val preparedStatement = connection.prepareStatement(query)
            preparedStatement.setString(1, nombre)
            preparedStatement.setString(2, modelo )
            preparedStatement.setInt(3, capacidad)
            preparedStatement.setBoolean(4, enServicio)
            preparedStatement.setInt(5, id_avion)

            preparedStatement.executeUpdate()
            preparedStatement.close()

            return true
        } catch (e: Exception) {
            println("No se pudo actualizar el avión seleccionado ${e.toString()}")
        }
        return false
    }

        fun listarAviones(): List<Avion> {
        val selectQuery = "SELECT * FROM AVION"
        val resultSet = statement.executeQuery(selectQuery)

        val aviones = mutableListOf<Avion>()

        try {
            while (resultSet.next()) {
                val id_avion = resultSet.getInt("ID_AVION")
                val nombre = resultSet.getString("NOMBRE")
                val modelo = resultSet.getString("MODELO")
                val capacidad = resultSet.getInt("CAPACIDAD")
                val enServicio = resultSet.getBoolean("ENSERVICIO")

                aviones.add(Avion(id_avion, nombre, modelo, capacidad, enServicio))
            }
        } catch (e: Exception) {
            println("Error al consultar Aviones: ${e.toString()}")
        } finally {
            resultSet.close()
        }

        return aviones
    }

    // CRUD RUTA
    fun crearRuta(id_ruta: Int,
                  origen: String,
                  destino: String,
                  duracion: Int,
                  fecha: String,
                  id_avion: Int): Boolean{
        val insertQuery = """
        INSERT INTO RUTA (ID_RUTA, ID_AVION, ORIGEN, DESTINO, DURACION, FECHA)
        VALUES ('$id_ruta', '$id_avion', '$origen', '$destino', '$duracion', '$fecha')
        """.trimIndent()
        try{
            statement.executeUpdate(insertQuery)
            return true
        }catch(e: Exception){
            println("A ocurrido un error al insertar un registro en la tabla Rutas: ${e.toString()}")
        }
        return false
    }

    fun eliminarRutaPorID(id: Int):Boolean{
        val query = """
            DELETE FROM RUTA
            WHERE ID_RUTA = '$id'
        """.trimIndent()
        try{
            statement.executeUpdate(query)
            return true
        }catch(e:Exception){
            println("Error al eliminar la ruta ${e.toString()}")
        }
        return false
    }

    fun listarRutas(): List<Ruta> {
        val selectQuery = "SELECT * FROM RUTA"
        val resultSet = statement.executeQuery(selectQuery)

        val rutas = mutableListOf<Ruta>()

        try {
            while (resultSet.next()) {
                val id_ruta = resultSet.getInt("ID_RUTA")
                val id_avion = resultSet.getInt("ID_AVION")
                val origen = resultSet.getString("ORIGEN")
                val destino = resultSet.getString("DESTINO")
                val duracion = resultSet.getInt("DURACION")
                val fecha = resultSet.getString("FECHA")

                rutas.add(Ruta(id_ruta, origen, destino, duracion, fecha, id_avion))
            }
        } catch (e: Exception) {
            println("Error al listar las rutas: ${e.toString()}")
        } finally {
            resultSet.close()
        }
        return rutas
    }

    fun actualizarRuta(id_ruta: Int,
                       id_avion: Int?,
                       origen: String?,
                       destino: String?,
                       duracion: Int?,
                       fecha: String?): Boolean{
        val updateQuery = """
        UPDATE RUTA 
        SET ID_AVION = COALESCE(?, ID_AVION), 
            ORIGEN = COALESCE(?, ORIGEN), 
            DESTINO = COALESCE(?, DESTINO), 
            DURACION = COALESCE(?, DURACION), 
            FECHA = COALESCE(?, FECHA)
        WHERE ID_RUTA = ?
    """.trimIndent()

        try {
            val preparedStatement = connection.prepareStatement(updateQuery)
            preparedStatement.setInt(1, id_avion?:-1)
            preparedStatement.setString(2, origen?:"")
            preparedStatement.setString(3, destino?:"")
            preparedStatement.setInt(4, duracion?:-1)
            preparedStatement.setString(5, fecha?:"")
            preparedStatement.setInt(6, id_ruta?:-1)
            preparedStatement.executeUpdate()
            preparedStatement.close()
            return true
        } catch (e: Exception) {
            println("Error al actualizar la ruta ${e.toString()}")
        }

        return false
    }

}