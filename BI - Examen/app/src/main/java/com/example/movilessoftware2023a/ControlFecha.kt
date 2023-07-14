package com.example.movilessoftware2023a

import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
class ManejoFechas {
    companion object {
        val formato = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        fun castFecha(string: String): LocalDate {
            return LocalDate.parse(string, formato)
        }
        fun imprimirFecha(fecha: LocalDate): String {
            return fecha.format(formato)
        }
    }
}




