/**
 * En esta clase se implementan los metodos para leer un fichero de texto en disco con los datos 
 * del tablero e ir introduciendo islas en la lista.
 * 
 * Tambien se implementean los metodos de salida de la solucion con los que se imprime el archivo 
 * de texto de la solucion.
 * 
 */
import java.io.*;
import java.lang.Character;
import java.util.ArrayList;
import java.util.HashSet;

class EntSal{

    private int dimension;
  
    public EntSal () {
        int dimension = 0;
         }
         
    public void ponerDimension (int dimension){
        
        this.dimension = dimension;
    }
    
    public int obtenerDimension (){
        
     return dimension;
        
    }
    /**
     * Dada la ruta en la que se encuentra el archivo del tablero construye la
     *  lista de islas 
     */
   public  ArrayList<Isla> creaListaDeIslas(String ruta){
     
      //File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      ArrayList<Isla> listaDeIslas = new ArrayList<Isla>() ;
     
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         if (ruta != null)
                br = new BufferedReader(new FileReader(ruta));
         else   {
                br = new BufferedReader(new InputStreamReader(System.in));
                if(!br.ready()){
                    System.out.println();
                    System.out.println("El fichero introducido no existe, o ha tecleado su nombre erroneamente");
                    System.exit(1);
                }
            }

         char caracterLeido;
         int fila = 0;
         int columna = 0;
         int valor;
        
         
         // Lectura del fichero, primero saco la dimension del hashi
         boolean dimensionLeida = false;
         String lineaLeida ;
         int numLeido;
         
         while(!dimensionLeida){
             
             lineaLeida = br.readLine();
                
             if ((lineaLeida.length() > 3) || (lineaLeida.contains("#") ))
                continue;
                
             numLeido = Integer.parseInt(lineaLeida);
             
             if( (numLeido > 0) && (numLeido <= 25)){
                 
                 ponerDimension(numLeido);
                 dimensionLeida = true;
                }
                         
            }
         // pasa por alto la siguiente linea
         lineaLeida = br.readLine();
         
         caracterLeido =  (char)br.read();                                      
         while(caracterLeido != '+') {
             
            if (caracterLeido == '\n') {
                ++fila;
                columna = 0;
            }
            else if (caracterLeido == ' ') {
            //si el cararcer leido es el espacio en blanco no lo cuento
            }
            else if (!(Character.isDigit(caracterLeido))) {
                ++columna; }
            else {
                valor = Character.getNumericValue(caracterLeido);
                // creo una nueva isla con los valores leidos
                Isla nuevaIsla = new Isla(fila,columna,valor);
                // y la meto en la lista de islas
                listaDeIslas.add(nuevaIsla);
                ++columna;
            }
            caracterLeido =  (char)br.read();
            
         }
       
         
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               
               fr.close();    
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
        }
      
        return listaDeIslas;
    } 
    
    /**
     * Imprime un tablero en un archivo de texto  con los los puentes tendidos 
     *  para eso se sirve del metodo puentesAarray que pasa primero los puentes
     *  a un array bidimensional
     * 
     * Por comodidad creara un array bidimensional con los datos de los puentes
     * que luego imprimira linea por linea con los metodos de impresion
     * 
     */
    public void imprimePuentes(char [ ][ ] arrayAimprimir){
        
            for(int i = 0; i <= 3 ; i++)
                System.out.println();
            
           //Imprime el array con la informacion de los puentes en el archivo de textolinea por linea 
           // esquema a seguir para trabajar con arrays:  for (i = 0; i <= x.length - 1; i++) 
           for(int fila = 0; fila <= dimension - 1 ; fila++)
            for(int columna = 0; columna <= dimension - 1 ; columna++){
                
                if(columna == dimension - 1)
                    System.out.println(arrayAimprimir[fila][columna]);
                else
                    System.out.print(arrayAimprimir[fila][columna] + " ");
                                
            }
    }
    
    /**
     * Pasa la informacion de los puentes a un array bidimensional que luego sera¡
     * mas facil de imprimir
     * 
     * Antes de utilizar propiamente este metodo tengo que hacer la operacion de 
     * union entre los puentes Verticales y Horizontales para crear el conjunto de
     * todos los puentes tendidos
     */
    public char [ ] [ ] puentesAarray (HashSet<Puente> puentesTendidos,ArrayList<Isla> listaDeIslas){
          
        char [ ][ ] arrayAimprimir = new char[dimension][dimension];
        //lleno al array de espacios en blanco : esquema a seguir para trabajar con arrays:  for (i = 0; i <= x.length - 1; i++) 
        for(int fi = 0; fi <= dimension - 1; fi++)
            for(int co = 0; co <= dimension - 1; co++)
                arrayAimprimir[fi][co] = ' ';
       
        //Meto las islas en el Array
        int fi;
        int co;
        for(Isla islaMirada : listaDeIslas){
            
            fi = Isla.obtenerFila(islaMirada);
            co = Isla.obtenerColum(islaMirada);
            String cadenaUnitariaA = Integer.toString (islaMirada.obtenerValor());
            arrayAimprimir[fi][co] = cadenaUnitariaA.charAt(0);    
        }
          
        for(Puente puenteMirado : puentesTendidos) { 
            //pone las islas del puente en el array
            int fiA = Isla.obtenerFila(Puente.obtenerIslaA(puenteMirado));
            int coA = Isla.obtenerColum(Puente.obtenerIslaA(puenteMirado));                     
            int fiB = Isla.obtenerFila(Puente.obtenerIslaB(puenteMirado));
            int coB = Isla.obtenerColum(Puente.obtenerIslaB(puenteMirado));
            
            //imprimo los arcos del puente en el array
            int [ ]columnas = Puente.obtenerColumnas(puenteMirado);
            int [ ]filas = Puente.obtenerFilas(puenteMirado);
            Tipo tipoPuente = Puente.obtenerTipo(puenteMirado);
            Orientacion orientacionPuente = Puente.obtenerOrientacion(puenteMirado);
            
            if(orientacionPuente == Orientacion.horizontal){
                int distColum= Math.abs(coA - coB) - 1 ;
                int columMin = Math.min(coA,coB);
                 while(distColum != 0){
                    columMin++;
                    if(tipoPuente == Tipo.simple)
                        arrayAimprimir[fiA] [columMin] = '-';
                    if(tipoPuente == Tipo.doble)
                        arrayAimprimir[fiA] [columMin] = '=';
                    distColum--;
                }
            }
            if(orientacionPuente == Orientacion.vertical){
                int distFila = Math.abs(fiA - fiB) - 1 ; 
                int filaMin = Math.min(fiA,fiB) ;
                while(distFila != 0){
                    filaMin++;
                    if(tipoPuente == Tipo.simple)
                        arrayAimprimir[filaMin] [coA ] = '|';
                    if(tipoPuente == Tipo.doble)
                        arrayAimprimir[filaMin] [coA] = 'H';
                    distFila--;
                }
            }
          
        }
          
        return arrayAimprimir;
        }
        
        
        /**
         * Imprime en el fichero dado en la ruta la situacion actual del Hashi con los puentes que 
         * se hayan tendido y los valores actuales de las islas
         */
        public void imprimirHashi( HashSet<Puente> puentesVerticales, HashSet<Puente>  puentesHorizontales,ArrayList<Isla> listaDeIslas){
        
             //Hago la union de los puentes verticales y horizontales en un solo conjunto de
             // puentes
             HashSet<Puente> conjuntoTodosLosPuentes = Puente.unirPuentes ( puentesVerticales, puentesHorizontales);
            
                    
             char arrayAimprimir [ ] [ ]= puentesAarray (conjuntoTodosLosPuentes,listaDeIslas);
             imprimePuentes(arrayAimprimir);
            
        }
        
        
}
