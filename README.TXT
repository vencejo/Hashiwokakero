------------------------------------------------------------------------
 README file.

------------------------------------------------------------------------

PROJECT TITLE: Hashiwokakero

PURPOSE OF PROJECT: Resolver todo tipo de puzzles Hashiwokakero de un tamaño 
                    de 7 x 7 hasta 25x25

VERSION or DATE: 21 de Julio del 2010

HOW TO START THIS PROJECT: En el directorio donde se encuentre el programa
                           teclee  en el terminal "java hashiwokakero ficheroDelPuzzle.txt"
                           donde ficheroDelPuzzle es el archivo de texto donde
                           guarda el Hashiwokakero a resolver

AUTHORS: Diego J. Martinez Garcia
            Centro asociado de Almería
            666659277
            apussapus@gmail.com

USER INSTRUCTIONS:
         
    Este programa puede ejecutarse con las siguientes sintaxis:
        
          hashiwokakero [-t][-h] [fichero]
      
         o bien
      
          hashiwokakero -l numero fichero
             
          o bien   
             
             hashiwokakero -b   
             
          ejemplos: 'java hashiwokakero -t  fichero.txt'    
                    'java hashiwokakero -l 1000 fichero.txt'    
                    'java hashiwokakero -h'    
                    'java hashiwokakero -b'    
             
          Opciones:   
             
          t: Modo traza. Muestra toda la secuencia de resolucion del hashi   
             mostrando los tableros intermedios .    
             
          b: Modo bateria de pruebas   realiza la resolucion de 41 hashis   
             uno tras otro   de manera continua e imprime un resumen de los tiempos .    
             
          l: Modo limite. si se activa este modo el siguiente argumento ha de    
              ser un numero que marcara el limite de nodos de la vuelta atras   
              el valor por defecto de l es de 2500 nodos                        
             
          h: Modo ayuda. Muestra la sintaxis y los créditos.    
             
          Si no tiene argumentos de seleccion de modo   el programa muestra la   
           matriz final resuelta   o un mensaje si no la encuentra.   
             
          Ello permite tambien usar redirecciones y 'pipes' como entrada de   
          datos.   
             
           ejemplos:   
               'hashiwokakero < ejemploHashiOficial.txt'    
               'type ejemploHashiOficial.txt | hashiwokakero'   
             
             
         
             ejemplo de un hashiwokakero:    
             
          #-------------------------   
          # Ejemplo hashiwokakero   
          # Fuente: Nikoli   
          # Valores de la Figura 1   
          # ------------------------   
          ##   
          13   
          13   
          2 * 3 * * * 4 * * * 2 * *   
          * * * * * * * * * * * * *   
          * * * * * * * * * * * * 2   
          * * * * * * * * * * * * *   
          1 * 1 * * * * * 1 * 3 * 3   
          * * * * * * * * * * * * *   
          2 * * * * * 8 * * * 5 * 2   
          * * * * * * * * * * * * *   
          3 * * * 3 * * * * * * * 1   
          * * * * * * * * * * * * *   
          * * * * 2 * * * * * 3 * 4   
          * * * * * * * * * * * * *   
          3 * * * * * 3 * 1 * * * 2   
          +   
