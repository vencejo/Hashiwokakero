
/**
 * Esta clase es la que recibe las opciones de la clase hashiwokakero y pone a funcionar el programa con las mismas
 *  
 *  entradaDatos: ruta del archivo con el hashi inicial
 *  
 *  traza:  es el modo de salida de los datos, si traza = 1 se mostraran los hashis intermedios 
 *          y si traza = 0 solo se mostrara el resultado final
 *    
 *  nodosLimite: si el programa alcanza ese numero de nodos en su funcionamiento considerará que está tardando demasiado e imprimirá un 
 *          mensaje solicitando al usuario permiso para continuar o para empezar con otros parametros   
 */
import  java.io.*;
import  java.util.*;

public class InterfaceUsuario
{
    private long t1, t2;
    private float dif;
    private boolean exito;
    public static int nodos;
    
    private String entradaDatos;
    private boolean traza;
    private int nodosLimite;
    private boolean ayudaAmostrar;

    /**
     * Constructor 
     */
    public InterfaceUsuario()
    {
        nodos = 0;
        nodosLimite = 2500;
        traza = false;
        ayudaAmostrar = false;
        
    }
   
    /**
     * Actualiza el campo entrada de datos
     */
     public void setNombreFichero(String nombreFichero){
         
         entradaDatos = nombreFichero;
         
        }
    /**
     * Actualiza el campo traza, poniendo el objeto interfaz en modo traza
     */
     public void setModoTraza(){
         
         traza = true;
         
        }
        
    /**
     * Actualiza el campo nodos limite
     */
     public void setNodosLimite(int limite){
         
         nodosLimite = limite;
         
        }
        
    /**
     * Actualiza el campo ayudaAmostrar
     */
    public void mostrarAyuda(){
        
        ayudaAmostrar = true;
    }
    /**
     * Muestra el campo ayudaAmostrar
     */
    public boolean getModoAyuda(){
        
        return ayudaAmostrar;
    }
    
    /**
     * Imprime la ayuda
     */
    public void ayuda(){
        
        System.out.println();
        System.out.println();
         String[] str={
         
         "Programa HASIWOKAKERO",
         "Desarrollado por Diego J. Martinez Garcia para la practica de",
         "Programacion III del curso 2009/2010 de Informatica de Sistemas, ",
         "",
         "   DNI:        45581069z",
         "   Lenguaje:   Java",
         "   IDE usado:  BlueJ",
         "   S.O:        Ubuntu y Windows Vista",
         "",
         "",
         "Este programa puede ejecutarse con las siguientes sintaxis:",
         "",
         "   hashiwokakero [-t][-h] [fichero]",
         "",
         "o bien",
         "",
         "   hashiwokakero -l numero fichero",
         "",
         "o bien",
         "",
         "   hashiwokakero -b",
         "",
         "ejemplos: 'hashiwokakero -t  fichero.txt' ",
         "          'hashiwokakero -l 1000 fichero.txt' ",
         "          'hashiwokakero -h' ",
         "          'hashiwokakero -b' ",
         "",
         "Opciones:",
         "",
         "t: Modo traza. Muestra toda la secuencia de resolucion del hashi",
         "   mostrando los tableros intermedios .", 
         "",
         "b: Modo bateria de pruebas, realiza la resolucion de 40 hashis",
         "   uno tras otro, de manera continua e imprime un resumen de los tiempos .", 
         "",
         "l: Modo limite. si se activa este modo el siguiente argumento ha de ",
         "    ser un numero que marcara el limite de nodos de la vuelta atras",
         "    el valor por defecto de l es de 2500 nodos                     ",
         "",
         "h: Modo ayuda. Muestra la sintaxis y los creditos.", 
         "",
         "Si no tiene argumentos de seleccion de modo, el programa muestra la",
         " matriz final resuelta, o un mensaje si no la encuentra.",
         "",
         "Ello permite tambien usar redirecciones y 'pipes' como entrada de",
         "datos.",
         "",
         " ejemplos:",
         "     'java hashiwokakero < ejemploHashiOficial.txt' ",
         "     'type ejemploHashiOficial.txt | java hashiwokakero'",
         "",
         "",
         
         "   ejemplo de un hashiwokakero: ",
         "",
         "#-------------------------",
         "# Ejemplo hashiwokakero",
         "# Fuente: Nikoli",
         "# Valores de la Figura 1",
         "# ------------------------",
         "##",
         "13",
         "13",
         "2 * 3 * * * 4 * * * 2 * *",
         "* * * * * * * * * * * * *",
         "* * * * * * * * * * * * 2",
         "* * * * * * * * * * * * *",
         "1 * 1 * * * * * 1 * 3 * 3",
         "* * * * * * * * * * * * *",
         "2 * * * * * 8 * * * 5 * 2",
         "* * * * * * * * * * * * *",
         "3 * * * 3 * * * * * * * 1",
         "* * * * * * * * * * * * *",
         "* * * * 2 * * * * * 3 * 4",
         "* * * * * * * * * * * * *",
         "3 * * * * * 3 * 1 * * * 2",
         "+",};
        for (int i=0;i<str.length;i++) System.out.println(str[i]);
    }

    /**
     * Metodo de inicio , que pone a trabajar  a las demas clases
     * 
     * Valores iniciales:
     * String entradaDatos = "C:/Users/Diego/Desktop/Practica Programacion 3/PracticaP3/banco de pruebas/ejemploHashi13_13.txt";
     *  
     */
    public void IniciarPrograma()
    {
        Calendar ahora1 = Calendar.getInstance();
        t1 = ahora1.getTimeInMillis();
        EntSal myEntSal = new EntSal();
        
        boolean malConstruido = false;
        
        System.out.println();
        System.out.println("Pretratando el tablero inicial ...");
        
        AlmacenDeHashis almacenHashis = new AlmacenDeHashis();
        
        Vuelta_Atras AlgoritmoVueltaAtras = new Vuelta_Atras(nodosLimite);
        
        Tratamiento datosPretratados = new Tratamiento(entradaDatos,traza );
        
        Hashi hashiInicial = datosPretratados.hashiInicial;
        
        /* Puede que el Tratamiento halla encontrado que el hashi no tiene solucion, en cuyo caso tendremos null en el hashi inicial */
        if(hashiInicial == null){
            exito = false;
            malConstruido = true;}
        /* Puede que el Tratamiento halla resuelto el hashi por si mismo */
        else if(hashiInicial.solucion()){
            hashiInicial.imprimirSolucion(Tratamiento.EntSalDatos,Tratamiento.obtenerRutaEntrada());
            exito = true;
        }
        else{
            System.out.println();
            System.out.println("Procesando nodos con el algoritmo de vuelta atras ..."); 
            exito = AlgoritmoVueltaAtras.vueltaAtras(hashiInicial, Tratamiento.EntSalDatos, traza);
        }
        Calendar ahora2 = Calendar.getInstance();
        t2 = ahora2.getTimeInMillis();
        
        dif =(float) (t2 - t1) / 1000;
        
        if(exito){
            System.out.println();
            System.out.println();
            System.out.println("Existe solucion que conecta todas las islas , \n solucion encontrada en  " + dif + " segundos.");}
        else if (malConstruido){
            System.out.println();
            System.out.println();
            System.out.println("No existe solucion ya que el hashi esta mal construido. ");}
        else if ( AlgoritmoVueltaAtras.obtenerNodos() < AlgoritmoVueltaAtras.obtenerNodosLimite()){
            System.out.println();
            System.out.println();
            System.out.println("El hashi no tiene solucion conexa, se ha explorado todo el arbol del juego \n sin encontrala. ");     
        }
        else{
            System.out.println();
            System.out.println();
            System.out.println("No se ha encontrado la solucion para el hashi en " + dif + " segundos con "+ nodos+ " nodos \n puede que el hashi no tenga solucion o que no se hallan explorado suficientes nodos \n si quiere hacer una exploración mas profunda comience el programa con un número mayor para los nodos limite.");
        }                                                  
        }
    }
        

