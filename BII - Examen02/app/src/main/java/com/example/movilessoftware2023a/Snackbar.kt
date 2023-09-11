package com.example.movilessoftware2023a
import android.view.View
import com.google.android.material.snackbar.Snackbar

public class Snackbar(var view: View? = null
) {
    fun mostrar(texto: String) {
            Snackbar.make(view!!,texto,Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }
}
