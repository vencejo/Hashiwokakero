
import java.util.ArrayList;
import java.lang.Comparable;
import java.util.Collections;

/**
 * Una clase que trabaja con listas de islas ordenadas
 */
public class Listas implements Comparable
{  
       
     private  ArrayList<Isla> listaDeIslas;
     private int longitud;
    
  
    /**
     * Constructor de la clase
     */
    public Listas()
    {
       
        listaDeIslas = new ArrayList<Isla>();
        longitud = 0;
      
    }
    
    
    /**
     * Constructor de la clase con parametros
     */
    public Listas(ArrayList<Isla> listaInicial)
    {
        
        listaDeIslas = listaInicial;
        longitud = listaInicial.size();
     
  
    }
    
    /**
     * Estractor de la longitud
     */
    public int obtenerLongitud(){
        
        return listaDeIslas.size();
    }
    
    
    /**
     * Estractor de la lista de islas
     */
    public ArrayList<Isla> obtenerListaDeIslas(){
        
        return listaDeIslas;
    }

    
   
    /**
     * Comparador de listas
     */
    public int compareTo(Object L) { 
        
        Listas otraLista = (Listas) L; 
        
        if(this.longitud < otraLista.obtenerLongitud()) 
            return -1; 
        else if(this.longitud == otraLista.obtenerLongitud()) 
            return 0; 
        else 
            return 1; 
        

    } 
    
    
    
     
    /**
     * Compara dos ArrayList de islas y devuelve verdadero si ambos tienen las mismas islas 
     */
    static public boolean igualdadListas (ArrayList<Isla> listaA, ArrayList<Isla> listaB){
        
        boolean iguales = true;
        int tamanoA = listaA.size();
        int tamanoB = listaB.size();
        
        if (tamanoA != tamanoB)
            return false;
             
        Isla [ ] arrayA = new Isla [listaA.size()];
        Isla [ ] arrayB = new Isla [listaB.size()];
        
        arrayA  = listaA.toArray(arrayA);
        arrayB  = listaB.toArray(arrayB);
        
        for (int i = 0; i < tamanoA; i++){
            
            if(!(Isla.identicas(arrayA[i],arrayB[i]))) {
                
                iguales = false;
                break;
            }
        }
        
        return iguales;
            
    }
    
    /**
     * Mira si una lista de islas contiene una isla con las coordenadas de la pasada como parametro
     */
    static public boolean contiene(Isla isla, ArrayList<Isla> listaIslas){
        
        boolean laContiene = false;
        
        for(Isla islaMirada : listaIslas){
            
            if(Isla.iguales(islaMirada, isla)){
                
                laContiene = true;
                break;
            }
        }
        return laContiene;
    }
    
    /**
     * Borra de una lista de islas la isla que tenga las mismas coordenadas que la pasada como 
     * parametro
     */
    static public void borra(Isla isla, ArrayList<Isla> listaIslas){
        
        for(Isla islaMirada : listaIslas){
            
            if(Isla.iguales(islaMirada, isla)){
                
                listaIslas.remove(islaMirada);
                break;
            }
        }
    }
    
     
 
    
                  
    
}
