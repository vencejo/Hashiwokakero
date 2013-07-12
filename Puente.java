
/**
 * En esta clase pongo los metodos que implementan y relacionan el objeto puente
 */
import java.util.ArrayList;
import java.lang.reflect.Array ;
import java.util.HashSet;

enum Tipo {simple, doble};
enum Orientacion {horizontal,vertical};

public class Puente  
{
    // instance variables - Un puente enlaza dos islas A y B y puede ser de tipo
    // simple o de tipo doble
    private Isla islaA;
    private Isla islaB;
    Tipo tipoPuente;   
    Orientacion orientacion;
    private int [ ]filas ;  //conjunto de filas y columnas  que cruzará el puente 
    private int [ ]columnas;  
    
    /**
     * Constructor
     */
    public Puente()
    {         
        }
         
    /**
     * Constructor 
     */
    public Puente(Isla islaInicial, Isla islaFinal)
    {
       this.islaA = islaInicial;
       this.islaB = islaFinal;
       this.tipoPuente = Tipo.simple;
       if(Isla.obtenerFila(islaInicial) == Isla.obtenerFila(islaFinal)){
            this.orientacion = Orientacion.horizontal;
            ponerColumnas();
        }
            
       else if (Isla.obtenerColum(islaInicial) == Isla.obtenerColum(islaFinal)){
            this.orientacion = Orientacion.vertical;
            ponerFilas();
        }
    }
    
    /**
     * Cloneador de puentes
     */
    public Puente clonar(){
        
        Puente puenteClonado = new Puente();
        
        //El siguiente código no sirve para cambiar el valor de la isla, se modifica también el de
        // la isla original: puenteClonado.islaA = islaA, si se hace así el 
        // clonado y el original compartirian las mismas islas.
        puenteClonado.islaA = islaA.clonar();
        puenteClonado.islaB = islaB.clonar();
        
        puenteClonado.filas = new int[filas.length];
        puenteClonado.columnas = new int[columnas.length];
        
        System.arraycopy(filas,0, puenteClonado.filas,0,filas.length);
        System.arraycopy(columnas,0, puenteClonado.columnas,0,columnas.length);
        
        puenteClonado.tipoPuente = tipoPuente;
        puenteClonado.orientacion = orientacion;
        
           
        return puenteClonado;
    }
  
 
    /**
     * Obtiene la islaA de un puente dado
     */
    static public Isla obtenerIslaA (Puente puente){
        return puente.islaA;
    }
    
    /**
     * Obtiene la islaB de un puente dado
     */
    static public Isla obtenerIslaB (Puente puente){
        return puente.islaB;
    }
     
    /**
     * Obtiene las filas de un puente dado
     */
    static public int [ ] obtenerFilas (Puente puente){
        return puente.filas;
    }
    
    /**
     * Obtiene las columnas de un puente dado
     */
    static public int [ ] obtenerColumnas (Puente puente){
        return puente.columnas;
    }
    
    /**
     * Obtiene el tipo de puente
     */
    static public Tipo obtenerTipo (Puente puente){
        return puente.tipoPuente;
    }
    
    /**
     * Obtiene la orientacion del puente
     */
    static public Orientacion obtenerOrientacion (Puente puente){
        return puente.orientacion;
    }
    
    /**
     * Pone un puente como simple
     */
    
    static public void ponerSimple(Puente puente){
        
        puente.tipoPuente = Tipo.simple;
    }
    
    /**
     * Pone un puente como doble
     */
    
    static public void ponerDoble(Puente puente){
        
        puente.tipoPuente = Tipo.doble;
    }
    
    /**
     * ponerFilas es un metodo auxiliar del constructor que marca las filas que cruza 
     *  un puente vertical
     */
    private void ponerFilas(){
        
        columnas = new int [ ] { Isla.obtenerColum(islaA) };
        int distancia = Math.abs (Isla.obtenerFila(islaA) - Isla.obtenerFila(islaB));
        int valorInicial =  Math.min (Isla.obtenerFila(islaA),Isla.obtenerFila(islaB));
        filas = new int [distancia + 1];
        int i = valorInicial;
        for(int j = 0; j <= distancia; j++){
            filas[j] = i;
            i++;
                }
        }    
    
     /**
     * ponerColumas es un metodo auxiliar del constructor que marca las columnas que cruza
     *  un puente horizontal
     */
    private void ponerColumnas(){
        
        filas = new int [ ] { Isla.obtenerFila(islaA) };
        int distancia = Math.abs (Isla.obtenerColum(islaA) - Isla.obtenerColum(islaB));
        int valorInicial =  Math.min (Isla.obtenerColum(islaA),Isla.obtenerColum(islaB));
        columnas = new int [distancia + 1];
        int i = valorInicial;
        for(int j = 0; j <= distancia ; j++){
            columnas[j] = i;
            i++;
                }
         }
        
    
    /**
     * Rastrea en el conjunto de puentes tendios a ver si hay alguno que conecte la islaInicial
     *  con la islaFinal, si es asi, devuelve el puente que las conecta, en caso contrario devuelve null
     */
    static public Puente mirarPuente(Isla islaInicial, Isla islaFinal, HashSet<Puente> conjuntoDePuentes){
        
        Puente puenteExistente = null;
        for(Puente puenteMirado : conjuntoDePuentes) {                                               
                                                                                               
            if(((Isla.iguales(puenteMirado.islaA,islaInicial)) && (Isla.iguales(puenteMirado.islaB,islaFinal))) ||
                ((Isla.iguales(puenteMirado.islaA, islaFinal))   && (Isla.iguales(puenteMirado.islaB,islaInicial)))){
                    puenteExistente = puenteMirado;    
                    break;
                }
                }
        return puenteExistente;
    
    }
    
    /**
     * Comprueba si hay un puente doble entre las dos islas en el conjunto de puentes
     */
    
    static public boolean puenteDoble (Isla islaInicial, Isla islaFinal, HashSet<Puente> conjuntoDePuentes){
        
        boolean doblePuente = false;
        
        Puente puenteExistente = mirarPuente(islaInicial, islaFinal,conjuntoDePuentes);
        
        if( (puenteExistente != null) && (puenteExistente.tipoPuente == Tipo.doble))
            doblePuente = true;
        
        return doblePuente;
    }
    
    /**
     *Devuelve verdadero si se puede hacer un puente entre las dos islas dadas, mirando
     *a ver si ya hay algun puente previo entre ambas
     */
        static public boolean puenteLibre(Isla islaInicial, Isla islaFinal, HashSet<Puente> conjuntoDePuentes, ArrayList<Isla> listaIslas) {
        
            boolean sePuedeTender = true;
            Puente puenteExistente = null;
        
            // Primero se revisa el conjunto de los puentes a ver si ya hay alguno
            // tendido entre estas dos islas
            puenteExistente = mirarPuente(islaInicial,islaFinal, conjuntoDePuentes);
       
            //Si lo hay, y es doble, no podemos tender mas puentes entre estas dos islas y se devuelve falso
            if((puenteExistente != null) && (puenteExistente.tipoPuente == Tipo.doble)) {
                sePuedeTender = false;
                return sePuedeTender;
            }
            
            if((puenteExistente != null) && 
                (!(Listas.contiene(islaInicial,listaIslas)) || !(Listas.contiene(islaFinal,listaIslas)))){
                sePuedeTender = false;
                return sePuedeTender;
            }
            
            return sePuedeTender;
        }
        
    /**
     * Mira a ver si hay algun puente cruzado en el trayecto de la islaA a la islaB
     */
     static public boolean puentesCruzados(Isla islaInicial, Isla islaFinal, HashSet<Puente> conjPuentesVerticales, HashSet<Puente> conjPuentesHorizontales) {
         //Antes de nada y para que no se produzcan errores en el siguiente algoritmo he de quitar del
         // conjunto de puentes Verticales y Horizontales pasados al método todos aquellos puentes que
         // salgan de la isla inicial y de la final
         
         //Hago una copia local de los subconjuntos que voy a estraer momentaneamente de los globales
         HashSet<Puente> conjPuentHorInicial = puentesDeLaIsla (islaInicial,conjPuentesHorizontales);
         HashSet<Puente> conjPuentVerInicial   = puentesDeLaIsla (islaInicial,conjPuentesVerticales);
         HashSet<Puente> conjPuentHorFinal = puentesDeLaIsla (islaFinal,conjPuentesHorizontales);
         HashSet<Puente> conjPuentVerFinal   = puentesDeLaIsla (islaFinal,conjPuentesVerticales);
         
         //Hago la diferencia de conjuntos entre los totales y los subconjuntos que tienen puentes
         // que parten de las islas consideradas
         conjPuentesHorizontales.removeAll(puentesDeLaIsla (islaInicial,conjPuentesHorizontales));
         conjPuentesVerticales.removeAll(puentesDeLaIsla (islaInicial,conjPuentesVerticales));
         conjPuentesHorizontales.removeAll(puentesDeLaIsla (islaFinal,conjPuentesHorizontales));
         conjPuentesVerticales.removeAll(puentesDeLaIsla (islaFinal,conjPuentesVerticales));
         
         boolean cruzados = false;
         // tiendo un puente provisional entre las dos islas
         Puente puenteProvisional = new Puente(islaInicial,islaFinal);
         // miro en el conjunto de los puentes ya tendidos a ver si alguno interfiere con las filas o columnas
         // del puente provisional
         if(puenteProvisional.orientacion == Orientacion.horizontal){
              for(Puente puenteMirado : conjPuentesVerticales) {
                  //miro si la fila del puente provisional está dentro de las filas del puente mirado
                  for(int i = 0; i < Array.getLength(puenteMirado.filas); i++) {
                      if(puenteProvisional.filas[0] == puenteMirado.filas[i]){
                        //miro si alguna de las columnas del puente provisional coincide con las del puente mirado
                        for(int j = 0; j < Array.getLength(puenteProvisional.columnas); j++){
                            if(puenteProvisional.columnas[j] == puenteMirado.columnas[0]){
                                cruzados = true;
                                break;
                            }
                                
                            }
                        }
                    }
                }
            }
         if(puenteProvisional.orientacion == Orientacion.vertical){
              for(Puente puenteMirado : conjPuentesHorizontales) {
                  //miro a ver si alguna de las columnas del puente mirado coincide con la columna del puente provisonal
                  for(int i = 0; i < Array.getLength(puenteMirado.columnas); i++) {
                      if(puenteProvisional.columnas[0] == puenteMirado.columnas[i]){
                        //miro si alguna de las filas del puente provisional coincide con la del puente mirado
                        for(int j = 0; j < Array.getLength(puenteProvisional.filas); j++){
                            if(puenteProvisional.filas[j] == puenteMirado.filas[0]){
                                cruzados = true;
                                break;
                            }
                            }
                        }
                    }
                }
            }
            
         // Deshago los cambios hechos en los conjuntos de puentes uniendo los subconjuntos con los globales
         conjPuentesHorizontales.addAll(conjPuentHorInicial);
         conjPuentesVerticales.addAll(conjPuentVerInicial);
         conjPuentesHorizontales.addAll(conjPuentHorFinal);
         conjPuentesVerticales.addAll(conjPuentVerFinal);
         return cruzados;
        }
        
        
    /**
     * Con este metodo actualizamos el conjunto de los puentes metiendo un nuevo puente al que 
     * introduce en el conjuntoDePuentes o que ha encontrado un puente simple en el
     * conjunto de puentes , en cuyo caso lo pone doble.
     * 
     * Atención! para usar este método primero nos tenemos que asegurar con el método puenteLibre
     *  de que se puede tender un puente entre las islas consideradas
     * 
     */
   static public void actualizarPuentes(Isla islaInicial, Isla islaFinal, HashSet<Puente> conjuntoDePuentes)
    {   
        
        Puente puenteExistente = mirarPuente(islaInicial,islaFinal, conjuntoDePuentes);
       
        //Si no hay puente tendido entre estas dos islas, lo creamos y lo 
        // metemos en el conjunto de puentes
        if(puenteExistente == null){
            Puente puenteTendido;
            puenteTendido = new Puente (islaInicial,  islaFinal);
            conjuntoDePuentes.add(puenteTendido);
            }
        //Si lo hay, y es simple, lo ponemos como doble
        else if(puenteExistente.tipoPuente == Tipo.simple){
                puenteExistente.tipoPuente = Tipo.doble; 
            }    
        
    }
    
   
    
    /**
     * Me da el conjunto de puentes que salen de la isla considerada
     */
    static public HashSet<Puente> puentesDeLaIsla (Isla isla, HashSet<Puente> conjuntoDePuentes){
    
        HashSet<Puente> puentesDesdeLaIsla = new HashSet<Puente>();
        
        for(Puente puenteMirado : conjuntoDePuentes) {                                               
            if((Isla.iguales (puenteMirado.islaA , isla)) || (Isla.iguales (puenteMirado.islaB , isla))) 
                    puentesDesdeLaIsla.add(puenteMirado);       
            }
        return puentesDesdeLaIsla;
        
    }
    
    /**
     * Me da la lista de las islas que componen un conjunto de puentes
     */
    static public ArrayList<Isla> islasDeLosPuentes (HashSet<Puente> conjuntoDePuentes){
    
        ArrayList<Isla> listaDeLasIslas = new ArrayList<Isla>();
        
        for(Puente puenteMirado : conjuntoDePuentes) {                                               
            if(!(listaDeLasIslas.contains(puenteMirado.islaA))) 
                    listaDeLasIslas.add(puenteMirado.islaA);
                    
            if(!(listaDeLasIslas.contains(puenteMirado.islaB))) 
                    listaDeLasIslas.add(puenteMirado.islaB);
            }
        return listaDeLasIslas;
        
    }
    
    /**
     * Me da una cuenta de  las vecinas que tienen puentes dirigidos a la isla considerada y que pueden tender
     * algun puente mas
     */
   static public int vecinasConPuentesAlaIsla (Isla isla, 
                                               HashSet<Puente> ConjuntoVerticales,
                                               HashSet<Puente> ConjuntoHorizontales,
                                               ArrayList<Isla> listaIslas){
      
       int cuenta =  0;
       HashSet<Puente> TodosLosPuentes = unirPuentes (ConjuntoVerticales, ConjuntoHorizontales);
       
       ArrayList<Isla> vecinasConPuentesParalaIsla = new ArrayList<Isla>();
       vecinasConPuentesParalaIsla = islasDeLosPuentes (puentesDeLaIsla (isla,TodosLosPuentes));
       
       ArrayList<Isla> consideradas = new ArrayList<Isla>();
       
       for(Isla vecinaMirada : vecinasConPuentesParalaIsla){
           
           if( !(puenteLibre(isla,vecinaMirada,TodosLosPuentes,listaIslas)) ||
                (Isla.iguales(vecinaMirada, isla)) || 
                (consideradas.contains(vecinaMirada)))
                continue;
           
           cuenta++;
           consideradas.add(vecinaMirada);
        }
            
       return cuenta;
        
    }
    
    /**
     * Da como resultado la union de dos conjuntos de puentes 
     */
    static public  HashSet<Puente> unirPuentes (HashSet<Puente> puentesVerticales, HashSet<Puente> puentesHorizontales){
        
       //Hago la unión de los puentes verticales y horizontales en un solo conjunto de
             // puentes
             HashSet<Puente> conjuntoTodosLosPuentes =new HashSet<Puente>();
             conjuntoTodosLosPuentes.addAll(puentesVerticales);
             conjuntoTodosLosPuentes.addAll(puentesHorizontales);
             
             return conjuntoTodosLosPuentes;
    }


    
    /**
     * Devuelve verdadero si dos puentes son iguales
     */
    static public boolean iguales(Puente puenteA, Puente puenteB){
        
        return ( (puenteA.islaA == puenteB.islaA) && 
                 (puenteA.islaB == puenteB.islaB) &&
                 (puenteA.tipoPuente == puenteB.tipoPuente) );   
        
    }
        
}