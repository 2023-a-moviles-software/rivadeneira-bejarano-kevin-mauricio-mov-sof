package com.example.movilessoftware2023a

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movilessoftware2023a.ui.theme.MovilesSoftware2023ATheme

class MainActivity : AppCompatActivity(){

    val callbackContenidoIntentExplicito=
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            ...
        }

    val callbackIntentPickUri=
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            result ->
            if(result.resultCode=== RESULT_OK){
                if(result.data!!.data != null){
                    val uri:Uri = result.data!!.data!!
                    val cursor = contentResolver.query(uri, null, null, null, null, null)
                    cursor?.moveToFirst()
                    val indiceTelefono = cursor?.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                    val telefono = cursor?.getString(IndiceTelefono!!)
                    cursor?.close()
                    "Telefono ${telefono}"
                }
            }
        }






    val callbackContenidoIntent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()
    ){
            result ->
            if(result.resultCode==Activity.RESULT_OK){
                if(result.data!=null){
                    val data = result.data
                    "${data?.getStringExtra("nombreModificado")}"
                }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
        /*val botonCicloVida= findViewById<Button>(
            R.id.btn_prueba1
        )
        botonCicloVida.setOnClickListener{
            irActividad(AcicloVida::class.java)
        }*/
        val botonListView = findViewById<Button>(
            R.id.btn
        )
    }

    fun abrirActividadConParametros(clase:class<*>){
        val intentExplicito = Intent(this, clase)
        //Evniar Parámetros
        //sE aceptan solo primitivas
        intentExplicito.putExtra("nombre","Kevin")
        intentExplicito.putExtra("Apellido","Rivadeneira")
        intentExplicito.putExtra("edad","22")
        //Recibimos respuesta
        callbackContenidoIntent.launch(intentExplicito)
    }




    val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
    botonIntentImplicito.setOnClickListener{
        val intentConRespuesta = Intent(
            Intent.ACTION_PICK,
            ContactsContract.CommonDataKinds.Phone.Content_URI
        )
    }
    val botonIntentexplicito = findViewById<Button>(R.id.btn_ir_intent_explicito)
    botonIntentExplicito.setOnClickListener{
        botonIntentexplicito.setOnClickListener{
            abrirActividadConParametros(CIntentExplicitoParametros::class.java)
        }
    }





    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
        // this.startActivity()
    }


}

