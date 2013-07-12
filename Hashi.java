
import java.util.HashSet;
import java.util.ArrayList;

/**
 * Utilizando los metodos y objetos de las clases Puente e Isla, implemento en esta clase todos los metodos que me
 * sirvan para trabajar con los tableros "Hashis"
 */
public class Hashi 
{  
      
    //En los conjuntos de puentes guardo la orientacion, el tipo (doble,simple), las filas que atraviesa cada puente 
    //y un par de referencias a las islas que conecta que se guardan  en la listaDeIslas 
    
     private  HashSet<Puente> conjuntoDePuentesVerticales;
     private  HashSet<Puente> conjuntoDePuentesHorizontales;
     private  ArrayList<Isla> listaDeIslas;
    
     // La lista de candidatas estara compuesta de las islas de la componente conexa
     //  susceptibles de tender un puente
     private  ArrayList<Isla> listaDeCandidatas;

    /**
     * Constructor de la clase
     */
    public Hashi()
    {
        // initialise instance variables
        conjuntoDePuentesVerticales = new HashSet<Puente>();
        conjuntoDePuentesHorizontales= new HashSet<Puente>();
        listaDeIslas = new ArrayList<Isla>();
        listaDeCandidatas = new ArrayList<Isla>();
    }
    
    
    /**
     * Constructor de la clase con parametros
     */
    public Hashi(ArrayList<Isla> listaInicial)
    {
        // initialise instance variables
        conjuntoDePuentesVerticales = new HashSet<Puente>();
        conjuntoDePuentesHorizontales= new HashSet<Puente>();
        listaDeIslas = listaInicial;
     
        // en la lista de candidatas pongo la primera isla de la lista inicial
        listaDeCandidatas = new ArrayList<Isla>();
        if(listaInicial.size() > 0)
            listaDeCandidatas.add(listaInicial.get(0));
    }
    
    /**
     * Constructor de la clase con todos los parametros
     */
    public Hashi(ArrayList<Isla> listaDeIslas,ArrayList<Isla> listaCandidatas ,
                  HashSet<Puente> conjuntoDePuentesVerticales,
                    HashSet<Puente>conjuntoDePuentesHorizontales )
    {
        this.conjuntoDePuentesVerticales = conjuntoDePuentesVerticales;
        this.conjuntoDePuentesHorizontales = conjuntoDePuentesHorizontales;
        this.listaDeIslas = listaDeIslas; 
        this.listaDeCandidatas = listaCandidatas;
    }
    
    /**
     * Estractor de la lista de islas
     */
    public ArrayList<Isla> obtenerListaDeIslas(){
        
        return listaDeIslas;
    }
    
    /**
     * Estractor de las candidatas
     */
    public ArrayList<Isla> obtenerListaCandidatas(){
        
        return listaDeCandidatas;
    }
     
    /**
     * Estractor de campos de la clase : devuelve los puentes verticales del Hashi
     */
    
    public HashSet<Puente> obtenerPuentesVerticales(){
        
        return conjuntoDePuentesVerticales;
        
    }
      
    /**
     * Estractor de campos de la clase: devuelve los puentes horizontales del Hashi
     */
    
    public HashSet<Puente> obtenerPuentesHorizontales(){
        
        return conjuntoDePuentesHorizontales;
        
    }
    
    /**
     * Clonador de Hashis
     */
    public  Hashi clonar(){
        
        ArrayList<Isla> listaClonada = new ArrayList<Isla>();
        listaClonada = clonarLista(listaDeIslas);
        
        ArrayList<Isla> CandidatasClonada = new ArrayList<Isla>();
        CandidatasClonada = clonarLista(listaDeCandidatas);
        
        HashSet<Puente> conjPuentVertClonados = new HashSet<Puente>();
        conjPuentVertClonados = clonarConjuntoPuentes(conjuntoDePuentesVerticales);
        
        HashSet<Puente> conjPuentHorClonados = new HashSet<Puente>();
        conjPuentHorClonados = clonarConjuntoPuentes(conjuntoDePuentesHorizontales);
        
        Hashi hashiClonico = new Hashi(listaClonada,CandidatasClonada, conjPuentVertClonados,conjPuentHorClonados);
        
        return hashiClonico;
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
   
   
   /**
     * Clonador de Conjunto de puentes Verticales
     */
    static public  HashSet<Puente> clonarConjuntoPuentes(HashSet<Puente> conjuntoOriginal) {
        
        HashSet<Puente> conjuntoClonico = new HashSet<Puente>();
        
        for(Puente puente: conjuntoOriginal) 
            conjuntoClonico.add(puente.clonar());
        return conjuntoClonico;
   }
   
   
    
    /**
     * Dadas dos islas me dice si se puede tender un puente entre ellas sin que cruce a una isla 
     * u otro puente entre ambas
     */
    
    public boolean sePuedeTender(Isla islaA, Isla islaB){
        
        return (!(Isla.iguales(islaA,islaB)) &&
                (islaA.islasAlaVista(islaA,islaB, Tratamiento.listaDeIslasMadre)) && 
                (!(Puente.puenteDoble(islaA,islaB,conjuntoDePuentesVerticales)) && 
                 !(Puente.puenteDoble(islaA,islaB,conjuntoDePuentesHorizontales))) &&
                (!(Puente.puentesCruzados(islaA, islaB,conjuntoDePuentesVerticales,conjuntoDePuentesHorizontales))));       
    }
    /**
     * Dadas dos islas me dice si existe un puente entre ambas
     */
    public boolean seHaTendido(Isla islaA, Isla islaB){
        
        boolean conectadas = false;
        Puente puenteVertical = new Puente();
        Puente puenteHorizontal = new Puente();
        
        puenteVertical =  Puente.mirarPuente(islaA, islaB,conjuntoDePuentesVerticales);
        puenteHorizontal =  Puente.mirarPuente(islaA, islaB,conjuntoDePuentesHorizontales);
            
        if ((puenteVertical != null) || (puenteHorizontal != null))
            conectadas = true;
            
        return conectadas;
    }
    
    /**
     * IslasVecinas me devuelve una lista con las islas vecinas a la isla considerada, con las que se esta en 
     *  condiciones de tender algun puente
     */
     public ArrayList<Isla> islasVecinas (Isla isla){
    
        ArrayList<Isla> listaDeVecinas = new ArrayList<Isla>();
             
        boolean vecinaEncontrada = false;
        
        for(Isla islaMirada : listaDeIslas){
            
            vecinaEncontrada = false;
            
            if ( sePuedeTender(islaMirada,isla)){
                
                vecinaEncontrada = true;          
                }     
            
            //HEURISTICA: si la vecina mirada no tiene a su vez vecinas, el puente con la isla original es 
            // obligado y por tanto , para favorecer cuanto antes su formacion, lo pongo al principio de la
            // lista de vecinas
            if ( vecinaEncontrada && (numeroVecinas(islaMirada) <= 2)){
            
                listaDeVecinas.add(0,islaMirada);
            }
            else if( vecinaEncontrada ){
                //HEURISTICA: para favorecer la creacion de puentes simples frente a los dobles
                //Si no hay ningun puente previo entre ambas, la isla mirada se pone al 
                // principio de la lista de vecinas
                if((Puente.mirarPuente(isla,islaMirada,conjuntoDePuentesVerticales) == null) &&
                    (Puente.mirarPuente(isla,islaMirada,conjuntoDePuentesHorizontales) == null))
                    
                    listaDeVecinas.add(0,islaMirada);
                else
                    //Si hay puente previo se pone al final de la lista de vecinas
                    listaDeVecinas.add(islaMirada);                
                
            }
               
        }
        
        return listaDeVecinas;
    }
    
    /**
     * Me devuelve una lista con las islas que estan conectadas , tienen puentes tendidos , a una isla dada
     */
    public ArrayList<Isla> conectadas (Isla isla){
        
        ArrayList<Isla> islasConectadas = new ArrayList<Isla>();
        
        for(Isla islaMirada : Tratamiento.listaDeIslasMadre){
            
            if(seHaTendido(isla, islaMirada))
                islasConectadas.add(islaMirada);         
        }        
        return islasConectadas;      
    }
    
    /**
     * Devuelve verdadero si las TODAS las islas y los puentes del hashi forman un conjunto conexo.
     * para ello uso un recorrido en anchura basado en la estructura de datos de la cola
     */
    public boolean conexo(){
         
        Isla islaInicial = new Isla();
        Isla islaSiguiente = new Isla();
      
        int numeroIslasHashi = Tratamiento.listaDeIslasMadre.size() ;
        int estado[] = new int[numeroIslasHashi ]; //Java inicializa a 0 por defecto todos los elementos de este vector
        
        Cola cola = new Cola <Isla>();
        
        //Tomo la primera isla que encuentre que tenga puente y la uso para, a partir de ella ir
        // mirando las islas que estan conectadas
        for(Isla islaMirada : Tratamiento.listaDeIslasMadre){
            
            if(conectadas(islaMirada).size() != 0){
                islaInicial = islaMirada;
                break;
            }
        }
      
        int indice = Tratamiento.listaDeIslasMadre.indexOf(islaInicial);
        estado[indice] = 1;
        cola.encolar(islaInicial);
        
        while (!cola.colaVacia()){
            islaSiguiente = (Isla) cola.desencolar();
            for(Isla islaMirada : conectadas(islaSiguiente)){
                
                if(estado[Tratamiento.listaDeIslasMadre.indexOf(islaMirada)] == 0){
                    
                    estado[Tratamiento.listaDeIslasMadre.indexOf(islaMirada)] = 1; //marca la isla como visitada
                    cola.encolar(islaMirada);                   
                }
            }
        }
        //El grafo sera conexo si todas las islas han sido visitadas ,( el estado de cada isla es igual a 1),
        // por lo que la suma del array de estados debe de dar igual al numero de islas
        int sumaEstados = 0;
        for (int i=0; i < estado.length; i++)
            sumaEstados += estado[i];
        boolean conex = (Tratamiento.listaDeIslasMadre.size() == sumaEstados);
        return conex;
       
    }
    /**
     * Me devuelve una lista con las islas que PUEDEN estar conectadas en un futuro , a una isla dada.
     * Si la isla no esta en la listaDeIslas me devuelve null ya que no puede tender ningun puente 
     * potencial al haberlos tendido ya todos los que podia
     */
    public ArrayList<Isla> potencialmenteConectadas (Isla isla){
        
        ArrayList<Isla> islasPotencialmenteConectadas = new ArrayList<Isla>();
        
        //Si la isla esta agotada devuelve una lista vacia
        if(!Listas.contiene( isla, listaDeIslas))
            return islasPotencialmenteConectadas;
            
        for(Isla islaMirada : Tratamiento.listaDeIslasMadre){
            
            if(sePuedeTender(isla, islaMirada) && Listas.contiene( islaMirada, listaDeIslas) )
                islasPotencialmenteConectadas.add(islaMirada);         
        }        
        return islasPotencialmenteConectadas;      
    }
    
    /**
     * Un hashi es potencialmenteConexo cuando las islas que ya no estan en la lista de islas (las islas "Agotadas" que han tendido todos
     * sus puentes) PUEDEN SER  conectadas mediante algún camino a alguna isla que aún está en la lista de Islas.
     * De no ser así se estarian formando regiones inconexas, incompatibles con la solucion correcta del problema.
     * Utilizo un recorrido en anchura basado en la estructura de datos de  cola para implementar este metodo.
     */
    public boolean potencialmenteConexo(){
        
        boolean loEs = true;
        boolean encontradaAislada = false;
        ArrayList<Isla> islasAgotadas = new ArrayList<Isla>();
        ArrayList<Isla> islasAgotadasConexas = new ArrayList<Isla>();
  
        //Genero la lista de las islas agotadas
        for(Isla islaMirada : Tratamiento.listaDeIslasMadre){
            
            if(!Listas.contiene( islaMirada, listaDeIslas))
                islasAgotadas.add(islaMirada);
            }
              
        //Recorro la lista de las islas agotadas buscando un camino potencial o real entre la isla agotada y
        // una isla por agotar
        ArrayList<Isla> listaConectadas = new ArrayList<Isla>();;
        Isla islaSiguiente = new Isla();
        int numeroIslasHashi = Tratamiento.listaDeIslasMadre.size() ;
        int estado[] = new int[numeroIslasHashi ];
        Cola cola = new Cola <Isla>();
        
        for(Isla islaAgotada : islasAgotadas){
            
            loEs = false; 
            int indice = Tratamiento.listaDeIslasMadre.indexOf(islaAgotada);
            for (int x=0; x < estado.length; x++)
                estado[x] = 0;
            estado[indice] = 1;
            cola.vaciarCola();
            cola.encolar(islaAgotada);
            
            listaConectadas.clear();
            boolean encontradoCamino = false;
            
            if((Isla.obtenerColum(islaAgotada) == 0) && (Isla.obtenerFila(islaAgotada) == 9))
                loEs = false;
                
            while (!cola.colaVacia()){
                islaSiguiente = (Isla) cola.desencolar();
            
                for(Isla isla1 : conectadas(islaSiguiente)){
                    listaConectadas.add(isla1);
                }
                for(Isla isla2 : potencialmenteConectadas(islaSiguiente)){
                    listaConectadas.add(isla2);
                }
                for(Isla islaMirada2 : listaConectadas){
                    
                    if(Listas.contiene( islaMirada2, listaDeIslas)){
                        loEs = true;
                        encontradoCamino = true;
                        break;
                    }
                    if(estado[Tratamiento.listaDeIslasMadre.indexOf(islaMirada2)] == 0){
                    
                        estado[Tratamiento.listaDeIslasMadre.indexOf(islaMirada2)] = 1; //marca la isla como visitada
                        cola.encolar(islaMirada2);                   
                    }
                }
                if(encontradoCamino){
                    break;
                }
            }
            if( !encontradoCamino)
                return false;          
            }
     
        return loEs; 
    }
    
    /**
     * Me da el numero de vecinas de una isla
     */
    public int numeroVecinas (Isla isla){
        
        int numero = 0;
        
        for(Isla islaMirada : listaDeIslas){
                                        
            if( sePuedeTender(islaMirada,isla))              
                ++numero;                 
        }     
        return numero;
    }
    
    /**
     * Puentes posibles me devuelve el numero de puentes que aun se pueden tender desde una isla a sus vecinos
     */
    public int puentesPosibles(Isla isla){
        
        int posibles = 0;
        //lista de vecinas a las que puedo tender algÃºn puente
        ArrayList<Isla> listaDeVecinas = islasVecinas(isla);
        
        for(Isla vecinaMirada : listaDeVecinas){
           
           //Si ya hay puente previo, o la isla vecina o la mirada valen 1, solo es posible un puente mÃ¡s entre
           // estas dos islas
           if((obtenerValorEnLista(isla) == 1) || (obtenerValorEnLista(vecinaMirada) == 1) ||
              (Puente.mirarPuente(isla,vecinaMirada,conjuntoDePuentesVerticales) != null) ||
              (Puente.mirarPuente(isla,vecinaMirada,conjuntoDePuentesHorizontales) != null))
                    
                posibles++;
               
           else
                //Si no hay puente previo y la isla vecina y la mirada valen mas de dos. se pueden tener
                // dos puentes
                posibles = posibles + 2;            
            }
        
        
        return posibles;
    }
    
    /**
     * Toma dos islas de la lista de islas , las enlaza  disminuyendo 
     * en uno el valor guardado en A y en B , crea un puente entre ambas y lo mete en el conjunto de 
     * los puentes, y quita a una o ambas islas de la lista de las islas por enlazar si sus valores 
     * llegan a cero
     */ 
    public void tenderPuente(Isla islaA, Isla islaB)
    {
        //quito temporalmente a las islas del puente que se va a tender de la lista de candidatas
        for(Isla candidataMirada : listaDeCandidatas){
            
            if(Isla.iguales(islaA, candidataMirada)){
                listaDeCandidatas.remove(candidataMirada);
                break;
            }
        }      
        for(Isla candidataMirada : listaDeCandidatas){
            
            if(Isla.iguales(islaB, candidataMirada)){
                listaDeCandidatas.remove(candidataMirada);
                break;
            }
        }    
        //Actualizo los conjuntos de puentes 
        if(Isla.obtenerColum(islaA) == Isla.obtenerColum(islaB))
            Puente.actualizarPuentes(islaA,islaB,conjuntoDePuentesVerticales);
        if(Isla.obtenerFila(islaA) == Isla.obtenerFila(islaB))
            Puente.actualizarPuentes(islaA,islaB,conjuntoDePuentesHorizontales);
           
        //actualizo la situacion de las islas en la lista,disminuyo en uno el valor de las islas que componen
        // el nuevo puente si, tras tender el puente,
        //el valor de estas ha llegado a 0 , tengo que quitarlas de la lista, ademas si su
        // valor es distinto de 0 tengo que volver a hacerla isla candidata      
        actualizarListaDeIslas(islaA);
        actualizarListaDeIslas(islaB);   
       // HEURISTICA para evitar la rapida formacion de puentes dobles:
       // si alguna de estas islas solo puede tender puentes dobles (tiene ya puentes con todas
       // sus vecinas ) la pongo al final de la lista de candidatas, recolocando la lista de candidatas          
       recolocaCandidatas();             
    }
    
    /**
     * Actualizo la situacion de las islas en la lista, si, tras tender el puente,
     * el valor de estas ha llegado a 0 , tengo que quitarlas de la lista , ademas si su
     * valor es distinto de 0 tengo que volver a hacerla isla candidata
     */   
   public void actualizarListaDeIslas(Isla isla) {
          
          // Busco en la lista de islas aquella que coincida en valores con la isla a
          // actualizar
          for(Isla islaMirada : listaDeIslas){
              
              if(Isla.iguales(isla, islaMirada)){
                    
                  if(obtenerValorEnLista(islaMirada) == 1) {
                        //si la isla, tras la disminucion de valor llegara a 0 la quito de la lista de islas 
                        // y de la lista de candidatas
                        listaDeIslas.remove(islaMirada);
                        Listas.borra(islaMirada, listaDeCandidatas);
                        break;
                                             
                    }
                  else{
                    // sin no, le disminuyo el valor en la lista
                   disminuyeValorEnLista(islaMirada);
                   // y la coloco en la lista de candidatas        
                   listaDeCandidatas.add(isla);
                    }
                }   
            }       
        }
  /**
   * HEURISTICA para evitar la rapida formacion de puentes dobles:
   * voy revisando la lista de las candidatas y si alguna de estas islas 
   * solo puede tender puentes dobles (tiene ya puentes con todas
   * sus vecinas ) la pongo al final de la lista de candidatas
   */      
  public void recolocaCandidatas(){
      
      boolean tieneUnoLibre = false;
      ArrayList<Isla> listaDeVecinas;
      int [ ] indiceCandidataArecolocar = new int[25*25 ];
    
      int i = 0;
      
      //primero reviso la lista de candidatas y voy tomando nota en un array de indices de las que luego tengo
      // que recolocar
      for(Isla candidataMirada : listaDeCandidatas){
          
            tieneUnoLibre = false;
            //primero genero la lista de vecinas de la isla
            listaDeVecinas = islasVecinas (candidataMirada);
       
            //si encuentro alguna vecina con la que no tenga tendido ningÃºn puente todavia, no tendrÃ©
            // que recolocar esta isla en las candidatas ya que puede tender aÃºn al menos un puente
            // simple
            for(Isla vecinaMirada : listaDeVecinas){
            
                if((Puente.mirarPuente(candidataMirada,vecinaMirada,conjuntoDePuentesVerticales) == null) &&
                    (Puente.mirarPuente(candidataMirada,vecinaMirada,conjuntoDePuentesHorizontales) == null)){
                        
                        tieneUnoLibre = true;
                        break;
                    }
                }
             
            if (!tieneUnoLibre){
                    indiceCandidataArecolocar[i] = listaDeCandidatas.indexOf(candidataMirada) ;
                    i++;
                }
            }
      // si he encontrado que es una isla que va a dar solo puentes dobles, la recoloco al final
      // de la lista de candidatas
      int j ;
      for(i = 0 ; i <= indiceCandidataArecolocar.length - 1 ; i++){
          
            if(indiceCandidataArecolocar[i] == 0 && indiceCandidataArecolocar[i + 1] == 0)
                break;
           
           
            Isla candidataArecolocar = listaDeCandidatas.get(indiceCandidataArecolocar[i]);      
            listaDeCandidatas.remove(listaDeCandidatas.get(indiceCandidataArecolocar[i]));
            listaDeCandidatas.add(candidataArecolocar);
            //disminuyo en uno los indices de las candidatas a recolocar a partir de i, para compensar el
            // hueco creado por la recolocacion
            j = i + 1;
            while(indiceCandidataArecolocar[j] != 0){
                
                indiceCandidataArecolocar[j]--;
                j++;                             
           }      
        }       
    }
    /**
     * Devuelve la lista de islas adyacentes a un puente
     */
    public ArrayList<Isla> islasAdyacentesAlPuente(Puente puente){
        
        ArrayList<Isla> adyacentes = new ArrayList();
        
        adyacentes.add(Puente.obtenerIslaA(puente));
        adyacentes.add(Puente.obtenerIslaB(puente));
        
        adyacentes.addAll(islasVecinas(Puente.obtenerIslaA(puente)));
        adyacentes.addAll(islasVecinas(Puente.obtenerIslaB(puente)));
        
        if(Puente.obtenerOrientacion(puente) == Orientacion.vertical){
            
            int [ ]filas = Puente.obtenerFilas (puente);
            
            for(Isla islaMirada : listaDeIslas){
                
                if((!adyacentes.contains(islaMirada)) && 
                    ((Isla.obtenerFila(islaMirada)) >= filas[0]) && (Isla.obtenerFila(islaMirada)) <= filas[filas.length - 1]){
                        
                        adyacentes.add(islaMirada);
                    }             
            }       
        }
        
        if(Puente.obtenerOrientacion(puente) == Orientacion.horizontal){
            
            int [ ]columnas = Puente.obtenerColumnas(puente);
            
            for(Isla islaMirada : listaDeIslas){
                
                if((!adyacentes.contains(islaMirada)) && 
                    ((Isla.obtenerColum(islaMirada)) >= columnas[0]) && (Isla.obtenerColum(islaMirada)) <= columnas[columnas.length - 1]){
                        
                        adyacentes.add(islaMirada);
                    }            
            }
        }      
        return adyacentes;
        
    }
    
    
    
  /**
   * Entiendo por "Isla Afortuanada" a aquella a la que le es indiferente la direccion que tome su 
   * proximo puente. 
   * Esta rodeada por una serie de vecinas y  a todas  tiene que tender al menos  
   * un puente.
   * Este es un metodo heuristico que devuelve verdadero si la isla es afortunada y esta forzada a tender una serie
   * de puentes. P.ej. una isla de valor 8 no tiene eleccion sobre los puentes a tender, igual que
   * una isla de valor 4 en una esquina , una de valor 6 en la pared etc...
   */       
  public boolean islaAfortunada(Isla isla){
      
      boolean afortunada = false;
      ArrayList<Isla> listaVecinas = islasVecinas (isla);
      
      int numeroDeVecinas = listaVecinas.size();
      int valorIsla = obtenerValorEnLista(isla);
        
       //Desglose por casos de la aparicion de una isla afortunada
       
       // isla afortunada de valor par
       if( (( valorIsla == 2) && (numeroDeVecinas == 1 ))  ||        
           (( valorIsla == 4) && (numeroDeVecinas == 2 ))  ||        
           (( valorIsla == 6) && (numeroDeVecinas == 3 ))  || 
           (( valorIsla == 8) && (numeroDeVecinas == 4 ))   )
           {
         afortunada = true;
         return afortunada;
        }
        
        //isla afortunada de valor impar
        if( (( valorIsla == 1) && (numeroDeVecinas == 1 ))  ||        
            (( valorIsla == 3) && (numeroDeVecinas == 2 ) && (vecinaUnitaria(isla)))  ||        
            (( valorIsla == 5) && (numeroDeVecinas == 3 ) && (vecinaUnitaria(isla)))  || 
            (( valorIsla == 7) && (numeroDeVecinas == 4 ) && (vecinaUnitaria(isla)))    )
           {
         afortunada = true;
         return afortunada;
        }
           
      return afortunada;
    }
    
  /**
   * Entiendo por "Isla semi Afortuanada" a aquella a la que le es indiferente la direcciones que tomen sus 
   *  puentes, menos el último , que queda indefinido.
   *  Se da en el caso de islas con valor impar
   *  P.ej. una isla de valor 3 rodeada por dos vecinas, una de valor 5 rodeada por 3 vecinas y una de valor
   *  7 rodeada por 4 vecinas
   */       
  public boolean islaSemiAfortunada(Isla isla){
      
      boolean SemiAfortunada = false;
      ArrayList<Isla> listaVecinas = islasVecinas (isla);
      
      int numeroDeVecinas = listaVecinas.size();
      int valorIsla = obtenerValorEnLista(isla);
        
      if( (( valorIsla == 3) && (numeroDeVecinas == 2 ))  ||        
            (( valorIsla == 5) && (numeroDeVecinas == 3 ) )  ||        
            (( valorIsla == 7) && (numeroDeVecinas == 4 ) ))    
           {
         SemiAfortunada = true;
         return SemiAfortunada;
        }
      return SemiAfortunada;
    }
    
    /**
     * Un Hashi sera parcialmente valido cuando:
     *  
     *   El valor de una isla (el num de puentes a tender a partir de una isla) sea menor o igual 
     *   al num de puentes posibles con sus vecinas a partir de esa isla en el momento actual.
     *      
     *   Con esto evito situaciones del tipo : (2)--(2) y (1)--(3)--(1) p.ej. donde el número de puentes  
     *   que se pueden tender es menor al necesario para dejar a cero los valores de las islas.
     *           
     */
    public boolean parcialmenteValido(){
        
        boolean valido = true;
        
         for(Isla islaVista : listaDeIslas){
         
             if(obtenerValorEnLista(islaVista) > puentesPosibles(islaVista)){
                valido = false;
                break;
                }
            }
    
        return valido;        
    }
    
    /**
     * CONDICIONES DE PODA
     * El metodo me dara verdadero si el ensayo hashi
     * al que se lo paso cumple las condiciones de poda
     */
    public boolean cumpleCondicionesDePoda(){
        
        boolean lasCumple = true;
        
        if(!potencialmenteConexo())
            return false;
        if(!parcialmenteValido())
            return false;
         
        return lasCumple;
            
    }         
    /**
     * Devuelve verdadero si la isla tiene una vecina de valor 1
     */
    public boolean vecinaUnitaria(Isla isla){
        
        boolean unitaria = false;
        ArrayList<Isla> listaVecinas = islasVecinas (isla);
        
        for(Isla islaVecina : listaVecinas){
                        
                        if(obtenerValorEnLista(islaVecina) == 1 ) {
                            unitaria = true;
                            break;
                        }
                    }
        return unitaria;     
    }
    
    
    
     /**
     * Obtiene el valor de una isla de coordenadas determinadas en la lista de islas
     */
    public int obtenerValorEnLista(Isla isla){
        
        int valor = 0;
        
        for(Isla islaMirada : listaDeIslas){
            
            if(Isla.iguales(islaMirada, isla)){
                
                valor = islaMirada.obtenerValor();
                break;
                     
            }
        }
        return valor;
    }
    
    /**
     * Disminuye en uno el  valor de una isla de coordenadas determinadas en la lista de islas
     */
    public void disminuyeValorEnLista(Isla isla){
        
        int valor;
        
        for(Isla islaMirada : listaDeIslas){
            
            if(Isla.iguales(islaMirada, isla)){
                
                valor = islaMirada.obtenerValor();
                islaMirada.ponerValor(valor - 1);
                break;
                     
            }
        }
    }
    
    
    
    /**
     * Dada una isla, busca a su homologa en  la lista del hashi
     */
    public Isla buscaIsla (Isla islaBuscada){
        
        Isla islaEncontrada = null;
        
        for(Isla islaMirada : listaDeIslas){    
            
            if( Isla.iguales(islaBuscada, islaMirada)) {
                
                islaEncontrada = islaMirada;
                break;
            }
        }
        
        return islaEncontrada;
    }
            
     /**
     * Imprime una posible solucion, (puede que sea parcial)
     */
    public void imprimirHashi(EntSal myEntSal, int nodo){
    
               myEntSal.imprimirHashi(conjuntoDePuentesVerticales,conjuntoDePuentesHorizontales,listaDeIslas);       
        
    }
    
    /**
     * Imprime la solucion final
     */
    public void imprimirSolucion(EntSal myEntSal,String rutaEntradaDatos){
    
               myEntSal.imprimirHashi(conjuntoDePuentesVerticales,conjuntoDePuentesHorizontales,Tratamiento.listaDeIslasMadre);       
        
    }
    
    /**
     * Devuelve verdadero si el Hashi representa una solucion valida para el tablero inicial
     */
    public boolean solucion(){
        
        return listaDeIslas.isEmpty() && conexo();
    }
                    
    
}
