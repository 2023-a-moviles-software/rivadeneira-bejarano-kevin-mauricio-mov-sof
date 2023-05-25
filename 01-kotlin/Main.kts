import java.util.Date

fun main(args: Array<String>){
    println("Hello World!")

    //INMUTABLES, NO SE REASIGNAN
    val inmutable: String ="Adrian";
    //inmutable="vicente";

    //MUTABLES, SE REASIGNAN
    var mutable: String ="vicente";
    mutable ="Adrian";


    //de preferencia se usan las inmutables val > var

    //Duck typing
    var ejemploVariable = " Kevin Rivadeneira"
    var edadEjemplo: Int = 12
    ejemploVariable.trim()
    //ejemploVariable = edadEjemplo;


    //VARIABLES PRIMITIVAS
    val nombreProfesor: String ="Kevin Rivadeneira"
    val sueldo: Double= 1.2
    val estadoCivil: Char ='C'
    val mayorEdad: Boolean = true
    //Clases java
    val fechaNacimiento: Date = Date;


    //SWITCH
    val estadoCivilWhen ="C"
    when(estadoCivilWhen){
        "C" -> {
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else -> {
            println("No Sabemos")
        }

        val esSoltero = (estadoCivilWhen == "S")
        val coqueteo = if(esSoltero) "Si" else "No"

    }


    //FUNCIONAES
    //void -> unit
    fun imprimirNombre(nombre: String): Unit{ // Unit es un void, es la variable de retorno
        println("Nombew : ${nombre}")
    }

    fun calcularSueldo(
            sueldo: Double, // Parámetro requerido
            tasa: Double = 12.00, // Parámetro por defecto
            bonoEspecial: Double?=null, // Parametro null
    ): Double{ //retorno
        //Int--> Int? //(nullable)
        //String -> String? (nullable)
        if(bonoEspecial == null){
            return sueldo*(100/tasa)
        }else{
            return sueldo * (100/tasa)+bonoEspecial
        }
    }


    calcularSueldo(10.00)
    calcularSueldo(10.00, 12.00, 20.00)
    calcularSueldo(10.00, bonoEspecial = =20.00)//Parámetros por nombre
    calcularSueldo(bonoEspecial = 20.00, sueldo=10.00, tasa=14.00)
}

abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(uno: Int, dos: Int) {
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }


    //Constructor primario
abstract class Numeros(protected val numeroUno: Int, protected val numeroDos: Int){ // Propiedad de la clase protected numeros.numeroUno
        //Ejemplo
        //uno: int    Parámetro sin modificador de acceso
        //private var uno: Int
    init{
        this.numeroUno; this.numeroDos;// this es opcional
        numeroUno; numeroDos; // sin el this, es lo mismo
        println("Inicializando");
    }
}







}