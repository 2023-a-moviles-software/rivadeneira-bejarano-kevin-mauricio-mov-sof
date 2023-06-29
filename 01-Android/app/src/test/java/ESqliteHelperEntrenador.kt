import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.movilessoftware2023a.BEntrenador

class ESqliteHelperEntrenador {
    contexto:Context?,
    ): SQLiteOpenHelper(
    contexto, "moviles", // nombre BDD
    null,
    1
    ){
        override fun onCreate(db:SQLiteDatabase){
            val scriptSQLCrearTablaEntrenador = """
                CREATE TABLE ENTRENADOR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion VARCHAR(50)
                )
            """.trimIndent()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion:Int, newVersion:Int){
            TODO("Not yet implemented")
        }
    }





    fun crearEntrenador(
        nombre: String,
        descripcion: String
    ): Boolean{
        val baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)
        val resultadoGuardar = baseDatosEscritura
            .insert(
                "Entrenador", //nombreTabla
                null,
                valoresAGuardar, //valores
            )
        baseDatosEscritura.close()
        return if(resultadoGuardar.toInt()===-1) false else true
    }



    fun eliminarEsntrenadorFormulario(id:Int):Boolean {
        val conexionEscritura = writableDatabase
        //where ID?
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "Entrenador" //NombreTAbla
                "id=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarEntrenadorFormulario(
        nombre: String,
        descripcion: String,
        id:Int,
    ):Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "ENTRENADOR", ///Nombre tabla
                valoresAActualizar,
                "id=?",
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if (resultadoActualizacion.toInt()==-1) false else true
    }

    fun consultarEntrenadorPorID(id:Int):BEntrenador{
        val baseDatosLectura = redeableDatabase
        val scriptConsultaLectura ="""
            SEELECT* FROM ENTRENADOR WHERE ID=?
        """.trimIndent()
        val parametrosConsultaLectura = arrayOf(id.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura, //consulta
            parametrosConsultaLectura, //parametros
        )
        //logica busqueda
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = BEntrenador(0,"","")
        val arreglo = arrayListOf<BEntrenador>()
        if(existeUsuario){
            do{
                val id= resultadoConsultaLectura.getInt(0)//indice 0
                val nombre = resultadoConsultaLectura.getString(1)
                val descripcion =  resultadoConsultaLectura.getString(2)
                if(id!=null){
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                }
            }while(resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado



    }
}