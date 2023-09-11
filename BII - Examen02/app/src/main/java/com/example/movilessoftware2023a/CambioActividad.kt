package com.example.movilessoftware2023a

import android.content.Context
import android.content.Intent

class CambioActividad(
    val context: Context,
    var callback: (intent: Intent) -> Unit = { }
) {
    fun cambiarActividad(clase: Class<*>) {
        val intent = Intent(context, clase)
        callback(intent)
        context.startActivity(intent)
    }
}