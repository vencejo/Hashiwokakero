import java.io.*;

/**
 * Programa que resuelve puzzles hashiwokakeros utilizando el algoritmo de la vuelta atras
 * 
 * @author Diego J. Martinez Garcia
 * @version 21/7/2010
 */
public class hashiwokakero
{
    
    private static String rutaEntradaDatos ;

    /**
     * Constructor 
     */
    public hashiwokakero()
    {
        rutaEntradaDatos = new String();  
    }

    /**
     * Metodo de arranque del programa
     */
    public static void main(String[] argumentos)throws Exception {
        
         InterfaceUsuario interfaz = new InterfaceUsuario();
         String nombreFichero;
         boolean entradadesdefichero = false;
         boolean traza = false;
         for (int i=0 ; i<argumentos.length ; i++){
             if (argumentos[i].charAt(0) == '-' ) {
                 for (int j=1; j<argumentos[i].length();j++ ){
                     switch (argumentos[i].charAt(j)){
                         case 'b':
                         case 'B':
                             BancoDePruebas.testBateriaDePruebas();
                             break;
                         case 'l':
                         case 'L':
                             interfaz.setNodosLimite(Integer.parseInt(argumentos[i + 1]));
                             break;                      
                         case 't':
                         case 'T':
                             interfaz.setModoTraza();
                             break;
                         case 'h':
                         case 'H':
                         case '?':
                             interfaz.mostrarAyuda();
                             break;
                         
                         default:
                             System.out.println();
                             System.out.println("error en la linea de comandos:");
                             System.out.println("   parámetro "
                                                 +argumentos[i].charAt(j)
                                                 +" desconocido");
                     }
                 }
             } 
             else{
                 if (!entradadesdefichero) {
                     java.io.File prueba = new java.io.File(argumentos[i]);
                     if (prueba.exists()){
                        interfaz.setNombreFichero(argumentos[i]);
                        entradadesdefichero=true;
                     }
                 }
                 else{
                     System.out.println();
                     System.out.println("error en la linea de comandos:");
                     System.out.println("   sobra el parámetro "+argumentos[i]);
                 }
            }
        }
        if (interfaz.getModoAyuda()) 
            interfaz.ayuda();
        else {
            
            interfaz.IniciarPrograma();
        }
     }
}
