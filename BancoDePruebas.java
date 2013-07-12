import  java.io.*;
import  java.util.*;
/**
 * En la clase bateria de pruebas paso al programa una serie de hashis de prueba para ver como responde
 */
public class BancoDePruebas
{
    private static long t1, t2;
    private static float dif;
    
    /**
     * Constructor for objects of class BateriaPruebas 
     */
    public  BancoDePruebas()
    {
   
    }

     public static  void testBateriaDePruebas()
    {
        Calendar ahora1 = Calendar.getInstance();
        t1 = ahora1.getTimeInMillis();
        //Ok
        InterfaceUsuario interfac1 = new InterfaceUsuario();
        interfac1.setNombreFichero("./banco de Pruebas/ejemploHashi7_7(2).txt");
        //interfac1.setModoTraza();
        interfac1.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac2 = new InterfaceUsuario();
        interfac2.setNombreFichero("./banco de Pruebas/ejemploHashi7_7.txt");
        interfac1.setModoTraza();
        interfac2.IniciarPrograma();
        
        
        
        //Ok
        InterfaceUsuario interfac4 = new InterfaceUsuario();
        interfac4.setNombreFichero("./banco de Pruebas/ejemploHashi7_7_n(2).txt");
        //interfac1.setModoTraza();
        interfac4.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac5 = new InterfaceUsuario();
        interfac5.setNombreFichero("./banco de Pruebas/ejemploHashi8_8_n2.txt");
        //interfac1.setModoTraza();
        interfac5.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac6 = new InterfaceUsuario();
        interfac6.setNombreFichero("./banco de Pruebas/ejemploHashi9_9(2).txt");
        //interfac1.setModoTraza();
        interfac6.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac7 = new InterfaceUsuario();
        interfac7.setNombreFichero("./banco de Pruebas/ejemploHashi9_9.txt");
        //interfac1.setModoTraza();
        interfac7.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac8 = new InterfaceUsuario();
        interfac8.setNombreFichero("./banco de Pruebas/ejemploHashi9_9_n.txt");
        //interfac1.setModoTraza();
        interfac8.IniciarPrograma();
           
        
        //Ok
        InterfaceUsuario interfac10 = new InterfaceUsuario();
        interfac10.setNombreFichero("./banco de Pruebas/ejemploHashi11_11.txt");
        //interfac1.setModoTraza();
        interfac10.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac11 = new InterfaceUsuario();
        interfac11.setNombreFichero("./banco de Pruebas/ejemploHashi13_13(2).txt");
        //interfac1.setModoTraza();
        interfac11.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac12 = new InterfaceUsuario();
        interfac12.setNombreFichero("./banco de Pruebas/ejemploHashi13_13.txt");
        //interfac1.setModoTraza();
        interfac12.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac122 = new InterfaceUsuario();
        interfac122.setNombreFichero("./banco de Pruebas/ejemploHashiOficial.txt");
        //interfac1.setModoTraza();
        interfac122.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac9 = new InterfaceUsuario();
        interfac9.setNombreFichero("./banco de Pruebas/ejemploHashi11_11(2).txt");
        //interfac1.setModoTraza();
        interfac9.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac13 = new InterfaceUsuario();
        interfac13.setNombreFichero("./banco de Pruebas/ejemploHashi15_15(2).txt");
        //interfac1.setModoTraza();
        interfac13.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac14 = new InterfaceUsuario();
        interfac14.setNombreFichero("./banco de Pruebas/ejemploHashi15_15(3).txt");
        //interfac1.setModoTraza();
        interfac14.IniciarPrograma();
        
         //Ok
        InterfaceUsuario interfac22 = new InterfaceUsuario();
        interfac22.setNombreFichero("./banco de Pruebas/ejemploHashi17_17(4).txt");
        //interfac1.setModoTraza();
        interfac22.IniciarPrograma();
        
         //Ok
        InterfaceUsuario interfac24 = new InterfaceUsuario();
        interfac24.setNombreFichero("./banco de Pruebas/ejemploHashi19_19(2).txt");
        //interfac1.setModoTraza();
        interfac24.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac33 = new InterfaceUsuario();
        interfac33.setNombreFichero("./banco de Pruebas/ejemploHashi23_23(3).txt");
        //interfac1.setModoTraza();
        interfac33.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac34 = new InterfaceUsuario();
        interfac34.setNombreFichero("./banco de Pruebas/ejemploHashi23_23(4).txt");
        //interfac1.setModoTraza();
        interfac34.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac38 = new InterfaceUsuario();
        interfac38.setNombreFichero("./banco de Pruebas/ejemploHashi25_25(4).txt");
        //interfac1.setModoTraza();
        interfac38.IniciarPrograma();
        
        
        
        //Ok
        InterfaceUsuario interfac15 = new InterfaceUsuario();
        interfac15.setNombreFichero("./banco de Pruebas/ejemploHashi15_15(4).txt");
        //interfac1.setModoTraza();
        interfac15.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac16 = new InterfaceUsuario();
        interfac16.setNombreFichero("./banco de Pruebas/ejemploHashi15_15.txt");
        //interfac1.setModoTraza();
        interfac16.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac17 = new InterfaceUsuario();
        interfac17.setNombreFichero("./banco de Pruebas/ejemploHashi17_17(2).txt");
        //interfac1.setModoTraza();
        interfac17.IniciarPrograma();
        
        
        
        //Ok
        InterfaceUsuario interfac23 = new InterfaceUsuario();
        interfac23.setNombreFichero("./banco de Pruebas/ejemploHashi17_17.txt");
        //interfac1.setModoTraza();
        interfac23.IniciarPrograma();
        
       
        
        //Ok
        InterfaceUsuario interfac25 = new InterfaceUsuario();
        interfac25.setNombreFichero("./banco de Pruebas/ejemploHashi19_19(3).txt");
        //interfac1.setModoTraza();
        interfac25.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac26 = new InterfaceUsuario();
        interfac26.setNombreFichero("./banco de Pruebas/ejemploHashi19_19(4).txt");
        //interfac1.setModoTraza();
        interfac26.IniciarPrograma();
        
        //Ok 
        InterfaceUsuario interfac27 = new InterfaceUsuario();
        interfac27.setNombreFichero("./banco de Pruebas/ejemploHashi19_19(5).txt");
        //interfac1.setModoTraza();
        interfac27.IniciarPrograma();
       
        
        
        //Ok
        InterfaceUsuario interfac29 = new InterfaceUsuario();
        interfac29.setNombreFichero("./banco de Pruebas/ejemploHashi21_21(2).txt");
        //interfac1.setModoTraza();
        interfac29.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac30 = new InterfaceUsuario();
        interfac30.setNombreFichero("./banco de Pruebas/ejemploHashi21_21(3).txt");
        //interfac1.setModoTraza();
        interfac30.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac31 = new InterfaceUsuario();
        interfac31.setNombreFichero("./banco de Pruebas/ejemploHashi21_21.txt");
        //interfac1.setModoTraza();
        interfac31.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac32 = new InterfaceUsuario();
        interfac32.setNombreFichero("./banco de Pruebas/ejemploHashi23_23(2).txt");
        //interfac1.setModoTraza();
        interfac32.IniciarPrograma();
        
        
        
        //Ok
        InterfaceUsuario interfac35 = new InterfaceUsuario();
        interfac35.setNombreFichero("./banco de Pruebas/ejemploHashi23_23.txt");
        //interfac1.setModoTraza();
        interfac35.IniciarPrograma();
        
        
        
        //Ok
        InterfaceUsuario interfac37 = new InterfaceUsuario();
        interfac37.setNombreFichero("./banco de Pruebas/ejemploHashi25_25(3).txt");
        //interfac1.setModoTraza();
        interfac37.IniciarPrograma();
        
        
        
        //Ok
        InterfaceUsuario interfac39 = new InterfaceUsuario();
        interfac39.setNombreFichero("./banco de Pruebas/ejemploHashi25_25(5).txt");
        //interfac1.setModoTraza();
        interfac39.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac40 = new InterfaceUsuario();
        interfac40.setNombreFichero("./banco de Pruebas/ejemploHashi25_25.txt");
        //interfac1.setModoTraza();
        interfac40.IniciarPrograma();
        
        //Ok       
        InterfaceUsuario interfac21 = new InterfaceUsuario();
        interfac21.setNombreFichero("./banco de Pruebas/ejemploHashi17_17(3).txt");
        //interfac1.setModoTraza();
        interfac21.IniciarPrograma();
        
       
        
        //Ok
        InterfaceUsuario interfac28 = new InterfaceUsuario();
        interfac28.setNombreFichero("./banco de Pruebas/ejemploHashi19_19.txt");
        //interfac1.setModoTraza();
        interfac28.IniciarPrograma();
        
        //Ok
        InterfaceUsuario interfac36 = new InterfaceUsuario();
        interfac36.setNombreFichero("./banco de Pruebas/ejemploHashi25_25(2).txt");
        //interfac1.setModoTraza();
        interfac36.IniciarPrograma();
        
        Calendar ahora2 = Calendar.getInstance();
        t2 = ahora2.getTimeInMillis();
        
        dif =(float) (t2 - t1) / 1000;
                    System.out.println();
                    System.out.println("Bateria de pruebas de 41 hashis acabada en " + dif/60 + " minutos " + "y " + dif % 60 + " segundos");
                    System.out.println("Lo que da una media de  " + dif/40 + " segundos por hashi ");
                    System.out.println();
                         
    }
}
