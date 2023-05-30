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
    val fechaNacimiento: Date = Date();


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

        //val esSoltero = (estadoCivilWhen == "S")
        //val coqueteo = if(esSoltero) "Si" else "No"

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
    calcularSueldo(10.00, bonoEspecial =20.00)//Parámetros por nombre
    calcularSueldo(bonoEspecial = 20.00, sueldo=10.00, tasa=14.00)
    val sumaUno = NumerosJava.Suma(1,1)
    val sumaDos = NumerosJava.Suma(null,1)
    val sumaTres = NumerosJava.Suma(1,null)
    val sumaCuatro = NumerosJava.Suma(null,null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(NumerosJava.Suma.pi)
    println(NumerosJava.Suma.elevarAlCuadrado(2))
    println(NumerosJava.Suma.historialSumas)

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


class Suma(//Constructorprimario suma    // Clase hija
        unoParametro: Int, //Parámetro
        dosParametro: Int,
): Numeros(unoParametro, dosParametro){//Extendiendo y mandando los parámetros(super)
    init{//Bloque de código de construtor primario
        this.numeroUno
        this.numeroDos
    }

    //Primer constructor nullable
    constructor(uno:Int?, dos: Int):this(//está extendiendo y mandando parámetros (Super)
            if(uno==null) 0 else uno,
            dos
    ){
        //Puede quedar vacío
    }

    //Segundo constructor nullable
    constructor(uno:Int, dos: Int?):this(//Parámetros
            uno,
            if(dos==null) 0 else dos
    ){
        //Puede quedar vacío
    }

    //Tercer constructor con 2 nullables
    constructor(uno:Int?, dos: Int?):this(//Parámetros
            if(uno==null) 0 else uno,
            if(dos==null) 0 else dos
    ){
        //Puede quedar vacío
    }


    //Métodos de la clase
    public fun sumar(): Int{ // Retorno int
        val total = numeroUno + numeroDos;
        agregarHistorial(total)
        return total
    }


    companion object{ // Atributos y metodos "Compartidos" singletons o static de esta clase
        //Todas las instancias d eesta clase comparten estos atributos
        // y métodos dentro del companion Object
        val pi = 3.14
        fun elevarAlCuadrado(num: Int): Int{
            return num*num;
        }
        val historialSumas = ArrayList<Int>()
        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma);
        }
    }
}
}