
/**
 * Esta clase se encarga de tratar los  Hashis realizando sucesivas pasasadas en busca de islas afortunadas y 
 * semi afortunadas para tender los puentes de las mismas y devolver al hashi ya tratado.
 * 
 * Esta clase se utiliza en dos contextos: 
 * 1- Con el método "generarHashiInicial" ,al iniciar el programa para realizar 
 * el pretratamiento del tablero inicial en busca de afortunadas antes de pasarselo al algoritmo de vuelta atras ,
 * esto puede facilitarle mucho las cosas al vuelta atras pues puede que este tratamiento simplifique sobremanera el tablero inicial.
 * 
 * 2- Dentro del algoritmo vuelta atras cuando se genera un ensayo tendiendo un puente entre las islas se llama seguidamente al 
 * metodo "tratarHashi"  para que compruebe si el nuevo puente ha provocado la creación de nuevas afortunadas o semiafortunadas.
 * 
 */
import  java.io.*;
import  java.util.*;

public class Tratamiento
{
   static private String rutaEntradaDatos ;   
   static public EntSal EntSalDatos;
  
   static public  ArrayList<Isla> listaDeIslasMadre;
   
   static public  ArrayList<Isla> listaDeIslasIniciales;
   static public  ArrayList<Isla> listaOriginal;
   static public Hashi hashiInicial;
   static public Vuelta_Atras vueltaAtras;

   
    /**
     * Constructor de la clase Tratamiento
     */
    public Tratamiento(String rutaEntradaDatos, boolean depuracion)
    {
        this.rutaEntradaDatos = rutaEntradaDatos;
        
        EntSalDatos= new EntSal();
        listaDeIslasMadre = EntSalDatos.creaListaDeIslas(rutaEntradaDatos);
        listaDeIslasIniciales = clonarLista(listaDeIslasMadre);
        listaOriginal = clonarLista(listaDeIslasMadre);
        hashiInicial = generarHashiInicial(listaDeIslasIniciales, depuracion);  
    }
    
    /**
     * Devuelve la ruta de entrada de los datos
     */
    public static String obtenerRutaEntrada()
    {
        return rutaEntradaDatos;
    }
    
    /**
     * Metodo para generar el Hashi inicial partiendo de la lista inicial de islas
     * y tendiendo los puentes de las islas afortunadas y semiafortunadas en sucesivas pasadas
     * 
     * El metodo devuelve null si los datos iniciales no representan a un hashi valido
     */
    public static Hashi generarHashiInicial(ArrayList<Isla> listaDeIslasIniciales, boolean depuracion){
       
        if(!inicialmenteValido(listaDeIslasIniciales))
            return null;
            
        EntSal myEntSal = new EntSal();
        Hashi hashiOriginal = new Hashi(listaOriginal);
        Hashi hashiInicio = new Hashi(listaDeIslasIniciales);
        boolean nuevasAfortunadas = false;
        
        int pasada = 0;
        hashiInicio = tratarHashi(hashiInicio,hashiOriginal,nuevasAfortunadas, pasada, myEntSal, depuracion);
        
        return hashiInicio;  
        
    }
    
    /**
     * Al tratar el Hashi voy tendiendo los puentes de las islas afortunadas y semi afortunadas en sucesivas pasadas, ya que con una
     * sola pasada no basta , porque el tender un puente puede hacer que las islas adyacentes se conviertan en afortunadas y al tender
     * los puentes de estas se formen nuevas afortunadas, creando así una cascada de afortunadas.
     */
    static public Hashi tratarHashi(Hashi hashiInicio, Hashi hashiOriginal,boolean nuevasAfortunadas, int pasada, EntSal myEntSal, boolean depuracion){
       
        
        hashiInicio = buscaAfortunadas(hashiInicio,hashiOriginal,nuevasAfortunadas, pasada, myEntSal, depuracion);
        if (hashiInicio == null)
            return null;
        hashiInicio = buscaSemiAfortunadas(hashiInicio,hashiOriginal,nuevasAfortunadas, pasada, myEntSal, depuracion);
        if (hashiInicio == null)
            return null;
                          
       // Hago sucesivas pasadas buscando las nuevas afortunadas que se pueden haber
       // creado tras la primera pasada
       do{
            nuevasAfortunadas = false;
            listaOriginal = clonarLista(hashiInicio.obtenerListaDeIslas());
            hashiOriginal = hashiInicio.clonar();
            
            hashiInicio = buscaAfortunadas(hashiInicio,hashiOriginal,nuevasAfortunadas, pasada, myEntSal, depuracion);
            if (hashiInicio == null)
                return null;
            hashiInicio = buscaSemiAfortunadas(hashiInicio,hashiOriginal,nuevasAfortunadas, pasada, myEntSal, depuracion);
            if (hashiInicio == null)
                return null;
            
        }while(nuevasAfortunadas);
       
       return hashiInicio; 
    }
    
    /**
     * Busca afortunadas: 
     * 
     * Busca islas afortunadas en el Hashi y va tendiendo sus islas, devuelve el hashi con todos los puentes
     * de las islas afortunadas tendidos
     * @param hashiInicio se le pasa este hashi para que busque islas afortunadas en el
     * @return hashiInicio devuelve el hashi con los puentes de las islas afortunadas tendidos
     * @return nuevasAfortunadas pone a verdadera la variable del metodo llamador "nuevasAfortunadas" si ha
     * encontrado afortunadas nuevas
     * 
     */
    static private Hashi buscaAfortunadas(Hashi hashiInicio,Hashi hashiOriginal,boolean nuevasAfortunadas, int pasada, EntSal myEntSal, boolean depuracion){
        
        int islasPretratadas = 0;
        for(Isla islaMirada : listaOriginal){
                
                if(hashiOriginal.islaAfortunada(islaMirada) ){
                
                    int valorInicio = hashiInicio.obtenerValorEnLista(islaMirada);
                    int valorOriginal = hashiOriginal.obtenerValorEnLista(islaMirada);
                    if (valorInicio == valorOriginal) {
                        //Se imprime un pequeño mensaje en pantalla que informe que el programa esta progresando
                        System.out.print("\\\\\\ \r");
                        Isla isla = hashiInicio.buscaIsla (islaMirada);
                        nuevasAfortunadas = true;
                
                        do{    
                            hashiInicio.tenderPuente(isla, hashiInicio.islasVecinas(isla).get(0));
                            valorInicio --;
                            
                            if(!hashiInicio.parcialmenteValido()){
                                hashiInicio = null;
                                return hashiInicio;}
           
                        }while (valorInicio > 0);
                        
                    if (depuracion)
                        hashiInicio.imprimirHashi(EntSalDatos, -(pasada++));
                    }                    
                }
            }
   
        return hashiInicio;
      
        }
        
    /**
     * Busca SemiAfortunadas: 
     * 
     * Busca islas Semi afortunadas en el Hashi y va tendiendo sus islas, devuelve el hashi con todos los puentes
     * de las islas Semi afortunadas tendidos.
     * 
     * Las islas semi afortunadas han de tender un solo puente a cada una de sus vecinas
     * 
     * @param hashiInicio se le pasa este hashi para que busque islas afortunadas en el
     * @return hashiInicio devuelve el hashi con los puentes de las islas afortunadas tendidos
     * @return nuevasAfortunadas pone a verdadera la variable del metodo llamador "nuevasAfortunadas" si ha
     * encontrado afortunadas nuevas
     * 
     */
    static private Hashi buscaSemiAfortunadas(Hashi hashiInicio,Hashi hashiOriginal,boolean nuevasAfortunadas, int pasada, EntSal myEntSal, boolean depuracion){
    
        int islasPretratadas = 0;
        for(Isla islaMirada : listaOriginal){
                
                if(hashiOriginal.islaSemiAfortunada(islaMirada) ){
                
                    int valorInicio = hashiInicio.obtenerValorEnLista(islaMirada);
                    int valorOriginal = hashiOriginal.obtenerValorEnLista(islaMirada);
                    ArrayList<Isla> vecinas = hashiInicio.islasVecinas(islaMirada);
                    
                    if (valorInicio == valorOriginal) {
                        //Se imprime un pequeño mensaje en pantalla que informe que el programa esta progresando
                        System.out.print("/// \r");
                        Isla isla = hashiInicio.buscaIsla (islaMirada);
                        nuevasAfortunadas = true;
                
                        do{    
                            hashiInicio.tenderPuente(isla, vecinas.get(0));
                            vecinas.remove(0);
                            valorInicio --; 
                            
                            if(!hashiInicio.parcialmenteValido()){
                                hashiInicio = null;
                                return hashiInicio;}
           
                        }while ((valorInicio > 1 && valorOriginal == 3) ||
                                (valorInicio > 2 && valorOriginal == 5) ||
                                (valorInicio > 3 && valorOriginal == 7));
                        
                    if (depuracion)
                        hashiInicio.imprimirHashi(EntSalDatos, -(pasada++));
                    }                    
                }
            }
        return hashiInicio;
      
        }
        
    /**
     * Para que un hashi sea inicialmente valido el valor acumulado de sus islas en la lista ha de ser par
     */
     public static boolean inicialmenteValido(ArrayList<Isla> listaDeIslasIniciales){
         
        int sumaValores = 0;
        
        for(Isla islaMirada : listaOriginal){
            
            sumaValores += islaMirada.obtenerValor();
            
        }
         
         return (sumaValores % 2 == 0);
         
        }
        
  
    
    /**
     * Clonador de lista de Islas
     */
    static public  ArrayList<Isla> clonarLista(ArrayList<Isla> listaOriginal) {
       
        ArrayList<Isla> listaClonica = new ArrayList<Isla>();
        for(Isla isla : listaOriginal){   
                
                listaClonica.add(isla.clonar());
        }      
        return listaClonica;
   }
}
