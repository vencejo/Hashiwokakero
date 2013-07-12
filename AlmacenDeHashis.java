import  java.util.*;
/**
 * En el almacen de hashis meto todos los hashis que ya han sido considerados para ir consultandolo cada vez
 * que genero un hashi nuevo y ver si este es repetido o no
 */
public class AlmacenDeHashis
{
  
   private static  ArrayList<Hashi> almacenHashis;

    /**
     * Constructor 
     */
    public AlmacenDeHashis()
    {
        almacenHashis = new ArrayList<Hashi>();
    }

    /**
     * Agrega hashis al almacen
     */
    static public void agregarAalmacenHashis(Hashi hashi){
        
        almacenHashis.add(hashi);
    }
    
    /**
     * Comprueba que un hashiEnsayo no esté duplicado en el Almacen de Hashis
     * 
     *  @param  hashi  el hashi  que vamos a estudiar
     * @param  almacen la lista donde estan guardados los hashis a comprobar
     * @return devuelve verdadero si el Hashi está ya en el almacen de Hashis 
     * (recordermos que en el almacen de hashis meto todos los hashis que ya han sido considerados ,
     * para evitar considerarlos mas de una vez)
     * */
     static public boolean hashiDuplicado(Hashi hashi){
     
         boolean hashiRepetido = false;
    
    
         for(Hashi hashiMirado : almacenHashis){
                        
             if(iguales(hashiMirado,hashi)){
                 hashiRepetido = true;
                 break;
                }    
            }
            return hashiRepetido;       
}

/**
 * Devuelve verdadero si dos hashis son iguales
 */
   static public boolean iguales(Hashi hashiA, Hashi hashiB){
       
       boolean igualdadListaIslas  ;
       boolean igualdadConjPuentesVert;
       boolean igualdadConjPuentesHori;
       
       igualdadListaIslas = Listas.igualdadListas(hashiA.obtenerListaDeIslas(),hashiB.obtenerListaDeIslas());
       
       igualdadConjPuentesVert = igualdadConjuntos(hashiA.obtenerPuentesVerticales(), hashiB.obtenerPuentesVerticales());
       igualdadConjPuentesHori =   igualdadConjuntos(hashiA.obtenerPuentesHorizontales(), hashiB.obtenerPuentesHorizontales());
       
       return igualdadListaIslas && igualdadConjPuentesVert && igualdadConjPuentesHori;
       
    }
    
    /**
     * Compara dos ArrayList de islas y devuelve verdadero si ambos tienen las mismas islas 
     */
    static public boolean igualdadConjuntos (HashSet<Puente> listaA, HashSet<Puente> listaB){
        
        boolean iguales = true;
        int tamanoA = listaA.size();
        int tamanoB = listaB.size();
        
        if (tamanoA != tamanoB)
            return false;
             
        Puente [ ] arrayA = new Puente [listaA.size()];
        Puente [ ] arrayB = new Puente [listaB.size()];
        
        arrayA  = listaA.toArray(arrayA);
        arrayB  = listaB.toArray(arrayB);
        
        for (int i = 0; i < tamanoA; i++){
            
            if(!(Puente.iguales(arrayA[i],arrayB[i]))) {
                
                iguales = false;
                break;
            }
        }
        
        return iguales;
            
    }

}
