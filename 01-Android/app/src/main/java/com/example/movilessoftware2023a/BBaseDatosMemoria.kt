package com.example.movilessoftware2023a

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init{
            arregloBEntrenador.add(BEntrenador(1, "Adrian", "a@a.com"))
            arregloBEntrenador.add(BEntrenador(2, "Kevin", "a@b.com"))


        }
    }
}