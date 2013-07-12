import java.util.HashSet;
import java.util.ArrayList;

/**
 * Una peque�a clase donde pongo los campos y los metodos utiles para trabajar con
 * las islas.
 */
public class Isla   
{   
    //codigo para posibilitar la copia (clonacion) de los objetos de esta clase
    
    
    private int fila;        //la fila donde esta la isla de la isla
    private int colum;       //la columna donde esta la isla
    private int valor;   //valor numerico de la isla
    
   
    
    //Habra un conjunto de puentes en el que iremos metiendo los puentes que se vayan haciendo entre islas
    private HashSet<Puente> conjuntoDePuentes = new HashSet<Puente>();
                                             
    /**
     * Constructor de la clase
     */
    public Isla(){
    }
    
     
    /**
     * Constructor de la clase
     */
     public Isla(int fil, int col, int val)
    {
    fila = fil;
    colum = col;
    valor = val;
    }
    
    
    /**
     * Obtiene la fila de una isla dada
     */
   static public int obtenerFila (Isla isla){
        return isla.fila;
    }
    
    /**
     * Obtiene la columna de una isla dada
     */
    static public int obtenerColum (Isla isla){
        return isla.colum;
    }
    
     /**
     * Obtiene el valor de una isla dada
     */
   public int obtenerValor (){
        return valor;
    }
    
    /**
     * Pone el valor de una isla
     */
     void ponerValor(int valorNuevo){
        
        valor = valorNuevo;
    }
    
    /**
     * Clonador de Islas
     */
    public Isla clonar(){
        
        Isla islaClonada = new Isla();
        
        islaClonada.fila = fila;
        islaClonada.colum = colum;
        islaClonada.valor = valor;
        
        return islaClonada;
    }
    
    /**
     * Me da la distancia entre dos islas
     */
    static public int distancia(Isla islaA, Isla islaB){
        
        int dist = 0;
        
        if (islaA.fila == islaB.fila)
            dist = Math.abs(islaA.colum - islaB.colum);
        else if (islaA.colum == islaB.colum)
            dist = Math.abs(islaA.fila - islaB.fila);
        
        return dist;
        
    }
    
    
     /**
     * enLinea devuelve verdadero si dos islas estan en la misma fila o la misma columna
     *  y por tanto son ,en principio, susceptibles de crear un puente entre ellas
     */
    static public boolean enLinea (Isla islaA, Isla islaB){
        
        return ((islaA.fila == islaB.fila) || (islaA.colum == islaB.colum));

    }
    
    /**
     * Devuelve verdadero si dos islas tienen las mismas coordenadas
     */
    static public boolean iguales(Isla islaA, Isla islaB){
        
        return( (islaA.fila == islaB.fila) && 
                (islaA.colum == islaB.colum) );
        
    }
    
    /**
     * Devuelve verdadero si dos islas tienen las mismas coordenadas y
     *  los mismos valores
     */
    static public boolean identicas(Isla islaA, Isla islaB){
        
        return( (islaA.fila == islaB.fila) && 
                (islaA.colum == islaB.colum) &&
                (islaA.valor == islaB.valor));
        
    }
    
    
    
    /**
     * IslasAlaVista devuelve verdadero si, en principio, se puede tender un puente entre
     *  las dos islas, este método no tiene en cuenta los posibles puentes cruzados que 
     *  habrán de revisarse con el correspondiente método de la clase Puente
     */
    public boolean islasAlaVista(Isla islaA, Isla islaB, ArrayList<Isla> listaDeIslas){
        
        return ((enLinea(islaA,islaB)) && 
                (!iguales(islaA,islaB)) && 
                (!(Isla.islasCruzadas(islaA,islaB,listaDeIslas))));
       }
   
      
   
    
    /**
     * Dada un par de islas mira a ver si hay alguna isla cruzada entre estas dos
     */
    static public boolean islasCruzadas(Isla islaA, Isla islaB, ArrayList<Isla> listaDeIslas) 
    {   
        boolean cruzadas = false;
        //  búsqueda horizontal
        if(islaA.fila == islaB.fila){
            for(Isla islaMirada : listaDeIslas) {
                if (islaMirada.fila == islaA.fila) {
                    if(((islaMirada.colum > islaA.colum) && (islaMirada.colum < islaB.colum)) ||
                        ((islaMirada.colum > islaB.colum) && (islaMirada.colum < islaA.colum))){
                            cruzadas = true;
                            break;
                        }
                        }
                    }
                }
                
        //  búsqueda vertical
        if(islaA.colum == islaB.colum){
            for(Isla islaMirada : listaDeIslas) {
                if (islaMirada.colum == islaA.colum) {
                    if(((islaMirada.fila > islaA.fila) && (islaMirada.fila < islaB.fila)) ||
                        ((islaMirada.fila > islaB.fila) && (islaMirada.fila < islaA.fila))){
                            cruzadas = true;
                            break;
                        }
                        }
                    }
                }
        return cruzadas;   
    }






}
