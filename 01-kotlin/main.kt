import java.util.Date
import java.util.ListResourceBundle

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
        println("Nombre : ${nombre}")
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


    //ARREGLOS


    //Tipos de arreglos

    //Arreglo estático
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico);

    //Arreglo dinámico
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)



    //OPERADORES: Sirven apra los arreglos estáticos
    // FOR EACH -> Unit    = void
    //Iterar un arreglo
    val respuestaForEach: Unit =arregloDinamico
        .forEach{ valorActual: Int ->
            println("Valor actual: ${valorActual}")

    }
    arregloDinamico.forEach{println(it)} // it significa el elemento iterado

    arregloEstatico
        .forEachIndexed{ indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)

    //MAP -> Muta el arreglo(Cambia el arreglo)
    //1) Enviemos el nuevo valor de la iteración
    //Nos devuelve el nuevo arreglo con valore smodificados

    val respuestaMap : List<Double> = arregloDinamico
        .map{ valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map{it +15}


    //Filter -> FILTRAR EL ARREGLO
    // 1) Devolver una expresión True o False
    // 2) Nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5 //Expresión condiicón
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter {it<=5  }
    println(respuestaFilter)
    println(respuestaFilterDos)


    //OR AND
    //OR -> ANY (Alguno cumple?)
    //AND -> ALL (Todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico
        .any{valorActual: Int ->
            return@any (valorActual>5)
        }
    print(respuestaAny) // true

    val respuestaAll : Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all(valorActual>5)
        }
    println(respuestaAll)// false


    //REDUCE -> Valor acumulado
    //Valor acumulado = 0 (Siempre 0 en lenguaje Kotlin)
    //[1,2,3,4,5] -> Sumeme todos los valores del arreglo
    //ValorIteración1 = valorEmpieza +1 = 0+1 = 1 -> Iteración 1
    //ValorIteración2 = valorIteracion1 +2 = 1+2 = 3 -> Iteración 2
    //ValorIteración3 = valorIteracion2 +3 = 3+3 = 6 -> Iteración 2

    val respuestaReduce: Int = arregloEstatico
        .reduce{ // acumulado=0 -> Siempre empieza en 0
            acumulado: Int, valorActual: Int ->
            return@reduce(acumulado+valorActual) // L+ogica de negocio
        }
    println(respuestaReduce)// 78


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