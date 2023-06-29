package com.example.movilessoftware2023a

import android.content.DialogInterface
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movilessoftware2023a.ui.theme.MovilesSoftware2023ATheme

class blistView : AppCompatActivity() {
    val arreglo= BBaseDatosMemoria.arregloBEntrenador
    var idItemSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Se renderizan los elementos del arreglo
        val listView = findViewById<ListView>(R.id.lv_list_view)
                                       //Contexto  //Layout xml a usar
        val adaptor = ArrayAdapter(this, android.R.layout.simple_list_item_1, arreglo)
        listView.adapter = adaptor
        adaptor.notifyDataSetChanged()//Notificamos los cambios a la interfaz
        val botonAnadirListView = findViewById<Button>(R.id.btn_anadir_list_view)
        botonAnadirListView.setOnClickListener{anadirEntrenador(adaptor)}
        registerForContextMenu(listView)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_editar ->{
                "Hacer algo con: ${idItemSeleccionado}"
                return true
            }
            R.id.mi_editar ->{
                "Hacer algo con: ${idItemSeleccionado}"
                return true
            }else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //Llenar las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.values.menu, menu)
        //Obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado= id
    }

    fun anadirEntrenador(adaptador: ArrayAdapter<BEntrenador>){
        arreglo.add(BEntrenador(4,"Kevin", "Descripción"))
        adaptador.notifyDataSetChanged()
    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this){
            builder.setTitle("Desea eliminar?")
            builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener{
                dialog,which -> //alguna cosa
            }
            )
            builder.setNegativeButton("Cancelar", null)
            val opciones = resources.getStringArray(
                R.array.string_array_opciones_dialogo
            )
            val seleccionado = booleanArrayOf(
                true, // Lunes seleccionado
                false,
                false,
            )

            builder.setMultiChoiceItems(opciones, seleccionado{
                dialog, which, isCheked ->
                "Dio clic en el item ${which}"
            })
            val dialogo = builder.create()
            dialogo.show()


        }
    }




}

