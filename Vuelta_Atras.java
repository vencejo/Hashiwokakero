
import  java.io.*;
import  java.util.*;

import java.lang.Cloneable;
import java.lang.Object;

/**
 * Esta es la clase  en la que implementamos el algoritmo de vuelta atras, utilizando los objetos
 *  de las demas clases
 */
public class Vuelta_Atras 
{  
   
   private int i;
   private int cota;
   
   private int longitudLista;

   private static final Comparator<Listas> comparador = new ListasOrdenadasPorLongitud();
     
   private  int nodos;
   
   // nodosLimite son el numero maximo de nodos que el algoritmo puede tratar antes de darse por vencido (vienen marcados por
   //  el usuario)
   private int nodosLimite;
   
   private  boolean acabado;
     
   private static class ListasOrdenadasPorLongitud implements Comparator<Listas>
   {
        public int compare(Listas t1, Listas t2)
        {
                return t1.compareTo(t2);
        }
    }
  
    /**
     * Constructor
     */
    public Vuelta_Atras()
    {   
       
        nodos = 0;
        acabado = false;
        nodosLimite = 2500;
        i = 1;
    }
    /**
     * Constructor para el caso de trabajar con hashis
     */
    public Vuelta_Atras(int limite)
    {   
       
        nodos = 0;
        acabado = false;
        nodosLimite = limite;
        i = 1;
         
    }
    
    public  int obtenerNodos(){
        
        return nodos;
    }
    public int obtenerNodosLimite(){
        
        return nodosLimite;
    }
    
    /**
     * Dado un Hashi de partida y un par de islas devuelve este hashi con las dos islas conectadas, además, 
     * si el nuevo puente ha provocado la creación de islas afortunadas a su alrededor se tienden los puentes de 
     * estas afortunadas
     * 
     * @param   hashiDePartida,  islaDePartida,  islaDeLlegada   el hashi y las dos islas iniciales
     * @return     hashiDePartida que ha sido modificado con los nuevos puentes, el metodo devuelve null si el
     *              hashi a devolver no cumple con las reglas del juego
     */
    public  Hashi generar_ensayo(Hashi hashiDePartida, Isla islaDePartida, Isla islaDeLlegada)
    {   
        
        //Tiendo el puente a partir del nuevo Hashi, las islas de partida y de llegada tienen que 
        // pertenecer a la lista del Hashi de partida
        hashiDePartida.tenderPuente(islaDePartida, islaDeLlegada);
        
       //HEURISTICA: Tras tender el puente,  realizo un tratamiento al hashi resultante en busca de posibles nuevas 
       // islas afortunadas o semiafortunadas
       if(!Tratamiento.inicialmenteValido(hashiDePartida.obtenerListaDeIslas()))
            return null;
            
       EntSal myEntSal = new EntSal();
       Hashi hashiOriginal = hashiDePartida;
       Hashi hashiInicio = hashiDePartida;
       boolean nuevasAfortunadas = false;
        
       int pasada = 0;
       boolean depuracion = false;
        
        hashiInicio = Tratamiento.tratarHashi(hashiInicio,hashiOriginal,nuevasAfortunadas, pasada, myEntSal, depuracion);
        
       return hashiInicio;
   
    }
    
    /**
     *  Las compleciones del algoritmo de vuelta atras: Las compleciones de un hashi dado seran una lista de hashis que
     *  se han generado a partir de el. Cada hashi nuevo generado se mira en la lista global de los hashis que ya han
     *  sido generados "almacenListas", si no esta en ella se le mete y si ya estaba se le descarta para las compleciones
     *  esto nos evita el tener que estar considerando hashis duplicados en las diferentes compleciones
     *  
     *  @param   hashiDePartida   el hashi sobre el que se van a hacer las compleciones
     *  @return     listaHashisNuevos lista con todos los hashis que se pueden generar a partir del hashi de partida
     */
     public  ArrayList<Hashi> compleciones(Hashi hashiDePartida){
         
        ArrayList<Hashi> listaHashisNuevos = new ArrayList<Hashi>();
        
        if(hashiDePartida.cumpleCondicionesDePoda()){  
            //recorro la lista de islas candidatas generando ensayos
            for(Isla islaMirada : hashiDePartida.obtenerListaCandidatas()){
                //genero la lista de las vecinas de la isla candidata a las que se esta¡ en condiciones de tender algun puente
                ArrayList<Isla> listaDeVecinas = hashiDePartida.islasVecinas(islaMirada);
                //voy generando un ensayo por cada isla vecina
                // e introduciendolo en la lista de Hashis a devolver
                for(Isla islaVecina : listaDeVecinas){
                    //Hay que evitar la duplicidad de los puentes dobles en la lista de compleciones
                    boolean hashiDuplicado = false;            
                    //Hago una copia local del hashi y las islas que me pasan, las modificaciones sobre estas copias no
                    // afectaran a los originales
                    Hashi hashiMirado = hashiDePartida.clonar();
                    Isla clon_islaMirada = islaMirada.clonar();
                    Isla clon_islaVecina = islaVecina.clonar();
                    Hashi hashiEnsayo = generar_ensayo( hashiMirado,clon_islaMirada,clon_islaVecina); 
                    //Puede que la generacion del ensayo me devuelva un hashi nulo, en ese caso es que el ensayo
                    // ha conducido a un hashi irresoluble y habra que probar con otro
                    if(hashiEnsayo == null)
                        continue; 
                        
                    //Puede que el hashi ensayo sea ya solución en cuyo caso limpio la lista de las compleciones
                    // encontradas hasta ahora, lo meto en ellas (para que solo este la solución)
                    //y devuelvo inmediatamente las mismas al vuelta atrás
                    
                    
                    if(hashiEnsayo.solucion()){
                        listaHashisNuevos.clear();
                        listaHashisNuevos.add(hashiEnsayo);
                        return listaHashisNuevos;
                    }
                    
                    //Miro a ver si el hashiEnsayo esta ya en el almacen de Hashis que ya fueron generados,
                    // si esta¡ en el almacen, no lo meto en la lista de compleciones y si no estaba en el almacen
                    // lo meto en el y en la lista de compleciones
                   hashiDuplicado = AlmacenDeHashis.hashiDuplicado(hashiEnsayo);
                    if(hashiDuplicado){
                        hashiDuplicado = false;
                        //no meto a este hashi duplicado en la lista de Hashis a entregar al Vuelta Atras
                        continue;
                    }
                    else{
                    AlmacenDeHashis.agregarAalmacenHashis( hashiEnsayo);                    
                    listaHashisNuevos.add(hashiEnsayo);              
                    // para que funcione el metodo generar_ensayo, la islaMirada y la vecina han de estar
                    // dentro de la lista de islas del hashiDePartida        
                     }         
       
               }
            }
        }
        //HEURISTICA: ordeno la lista de hashis nuevos según la longitud de su lista de canditatas de menor a 
        // mayor, generalmente la accion de poner primero las compleciones mas restrictivas hace más eficiente la vuelta atras (wikipedia)
        listaHashisNuevos = OrdenarHashisSegunCandidatas (listaHashisNuevos);
        return listaHashisNuevos;
}
    
    
    /**
     * El algoritmo de vuelta atras
     * 
     *  @param   hashi   el hashi sobre el que se va a aplicar el algoritmo
     *  @param   myEntSal datos para poder imprimir la solucion
     *  @param   depuracion si es =1 se activa la depuracion y se imprimen los hashis intermedios
     *           y si es =0 solo se imprime la solucion final
     *  
     *  @return acabado  me indica si se ha llegado al final del hashi o no
     */
    public  boolean vueltaAtras(Hashi hashi, EntSal myEntSal, boolean depuracion){
        
        
        if (InterfaceUsuario.nodos == 0){
            cota = Tratamiento.hashiInicial.obtenerListaDeIslas().size();
        }
            
        ++InterfaceUsuario.nodos;
        nodos = InterfaceUsuario.nodos;
        
        if (nodos == 5)
            acabado = false;
                    
        //si el hashi es solución lo imprimo
        if(hashi.solucion()){
            hashi.imprimirSolucion(myEntSal,Tratamiento.obtenerRutaEntrada());
            acabado = true;
            return true;
            }
        else if (nodos > nodosLimite){
            EntSal miEntSal = new EntSal();
            System.out.println();
            System.out.println();
            System.out.println("Se han alcanzado los nodos maximos en la busqueda de la solucion: "+ (nodos - 1) + " nodos"); 
            System.out.println("Si quiere seguir buscando la solucion, comience el programa con un valor mayor para el maximo de los nodos ");
            //Acabo aqui el programa
            System.exit(1);
        }
        else {     
            //HEURISTICA: Hago una revision de los avances del algoritmo cada diez nodos: si no se ha disminuido significativamente el numero
            // de islas (si no ha disminuido la cota) es que no hay avances y entonces recomienzo con el siguiente hashi de las compleciones del hashi inicial
            // .Si por el contrario si se han producido avances, actualizo el valor de la cota , disminuyendolo al numero de islas actual
            if(InterfaceUsuario.nodos == 10 * i){
                if( hashi.obtenerListaDeIslas().size() <= cota){
                    cota = hashi.obtenerListaDeIslas().size();
                    i++;
                     }
                else{
                    hashi = compleciones(Tratamiento.hashiInicial).get(i);
                    i++;
                }
            }
            
            ArrayList<Hashi> complecionesHashi = compleciones(hashi);
            
            for(Hashi hashiMirado : complecionesHashi){
                
                // sentencia necesaria para volver de las llamadas recursivas
                //al metodo pruebaVueltaAtras
                if(acabado)
                    return true;
                
                // En el modo depuracion imprimo los resultados intemedios
                if(depuracion)
                    hashiMirado.imprimirHashi(myEntSal,InterfaceUsuario.nodos);
               
                System.out.print(" \t Procesados "+ nodos   + " nodos \r"); 
                vueltaAtras(hashiMirado, myEntSal,depuracion);
               
           }                     
        }
        
      return acabado;
   }
   
/**
 * Ordena un ArrayList de Hashis segun el tamaño de sus listas de candidatas 
 */
private ArrayList<Hashi> OrdenarHashisSegunCandidatas (ArrayList<Hashi> listaHashis){

    listaHashis = Ordenar_Insercion(listaHashis);
    
    return listaHashis;

}

/**
 * Ordena un ArrayList de Hashis segun el tamaño de sus listas de candidatas por el algoritmo de insercion
 */

public static ArrayList<Hashi> Ordenar_Insercion(ArrayList<Hashi> listaHashis){
     
       
        for (int i = 1; i < listaHashis.size(); i++) {
            
            Hashi temp = listaHashis.get(i);
            int j;
            for (j = i- 1; j >= 0 && temp.obtenerListaCandidatas().size() < listaHashis.get(j).obtenerListaCandidatas().size()  ; j--) {
                
                listaHashis.set(j + 1 , listaHashis.get(j));
            }
            listaHashis.set(j + 1,  temp);
               
            }
        return listaHashis;
    }

}








