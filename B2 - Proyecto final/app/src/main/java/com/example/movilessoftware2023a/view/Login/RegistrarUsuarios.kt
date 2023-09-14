package com.example.app_proyecto2.view.Login

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.app_proyecto2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RegistrarUsuarios : AppCompatActivity() {

    //Instancia de firebase authentication
    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuarios)

        //Regresar a la pantalla inicial
        val textRegresar = findViewById<TextView>(R.id.tv_regresarRegistrar)
        textRegresar.setOnClickListener {
            abrirActividad(MainActivity::class.java)
        }

        //Registrarse
        val botonRegistrar = findViewById<Button>(R.id.btn_registrarseRegistrar)
        botonRegistrar.setOnClickListener {
            //Obtener todos los datos
            val nombre = findViewById<EditText>(R.id.et_nombreRegistro).text.toString()
            val apellido = findViewById<EditText>(R.id.et_apellidoRegistro).text.toString()
            val correo = findViewById<EditText>(R.id.et_correoRegistro).text.toString()
            val contrasena = findViewById<EditText>(R.id.et_contrasenaRegistro).text.toString()
            val contrasenaConfirmacion = findViewById<EditText>(R.id.et_contrasenaConfirmarRegistro).text.toString()
            //Llamar al método de registro
            registrarUsuario1(nombre,apellido, correo, contrasena, contrasenaConfirmacion)
        }

    }

    fun registrarUsuario1(nombre : String, apellido: String, correo: String, contrasena: String, contrasenaConfirmacion: String) {
        //Comprobar que los datos no sean nulos o vacios
        if (!nombre.isBlank() && !apellido.isBlank() && !correo.isBlank() && !contrasena.isBlank() && !contrasenaConfirmacion.isBlank()){
            //Comprobar el largo de la contraseña
            if (contrasena.length >=6 && contrasenaConfirmacion.length>=6){
                //Comprobar que la contraseña es igual
                if(contrasena == contrasenaConfirmacion){
                    //Metodo de creación
                    mAuth.createUserWithEmailAndPassword(correo, contrasena)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                crearRegistro(nombre, apellido, correo, contrasena)
                                abrirMensaje()
                            } else {
                                Toast.makeText(applicationContext, "Revisar que los campos ingresados sean correctos", Toast.LENGTH_SHORT).show()
                            }
                        }
                }else{
                    Toast.makeText(applicationContext,"Las contraseñas no son iguales",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(applicationContext,"La contraseña debe tener al menos 6 caracteres",Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(applicationContext,"Revise que todos los campos estén llenos",Toast.LENGTH_SHORT).show()
        }
    }

    fun crearRegistro(nombre : String, apellido: String, correo: String, contrasena: String){

        //Referencia a la base de datos
        val db = Firebase.firestore
        val referencia = db.collection("Usuario")

        //Obtener todos los datos requeridos
        val nuevoUsuario = hashMapOf<String, Any>(
            "uid" to mAuth.currentUser!!.uid,
            "nombre" to nombre,
            "apellido" to apellido,
            "correo" to correo
        )

        //Almacenar usuario
        referencia
            .document(correo)
            .set(nuevoUsuario)
            .addOnSuccessListener {
                Log.i("Firebase", "Creación de usuario exitosa")
            }
            .addOnFailureListener {
                Log.i("Firebase", "Creación de usuario fallida")
            }
    }

    fun abrirMensaje(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Registrado con éxito")
        builder.setMessage("Bienvenido a su sistema de gestión de tratamiento médico")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{ dialog, which ->
                abrirActividad(MainActivity::class.java)
                Log.i("Dialogo","Creación exitosa del usuario")
            }
        )
        val dialogo = builder.create()
        dialogo.show()
    }

    fun abrirActividad(actividad: Class<*>) {
        val intent = Intent(this, actividad)
        startActivity(intent)
    }
}



