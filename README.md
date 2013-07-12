Proyecto Final de la UNED (Curso 09-10)
===========================================================
Lenguaje Java

Enunciado del Proyecto
----------------------
[Hashiwokakero](http://es.wikipedia.org/wiki/Hashiwokakero) es un puzzle lógico original de Nikoli.
El pasatiempo consiste en un tablero rectangular, aunque el tablero en sí no se suele
dibujar, en el que algunas celdas contendrán números con valores del 1 al 8, que
aparecen dentro de un círculo y que representan “islas”. El resto de las celdas estarán
vacías. El objetivo es conectar todas las islas dibujando “puentes” (aristas) entre las
islas. Los puentes deben cumplir las siguientes condiciones:

Deben comenzar y acabar en distintas islas mediante una línea recta.
No pueden cruzar otros puentes u otras islas.
Las líneas rectas deben ser horizontales o verticales.
Como mucho puede haber dos puentes paralelos que conecten un par de islas.
Al final, el número de puentes conectados a cada isla debe coincidir con el
número de la isla.


Funcionamiento
--------------
El programa se puede invocar desde la línea de comandos de Linux (ó
Windows) con la siguiente sintaxis:

$> java hashiwokakero [-h] [fichero]

Los argumentos son:
fichero: el nombre del archivo que contiene los datos de entrada.
-h: modo ayuda. Muestra la sintaxis y los créditos.

El programa leerá los datos del fichero que se le pase como argumento. Por ejemplo:

C:\> java hashiwokakero tablero.txt <ENTER>

o también desde la entrada estándar (stdin):

C:\> java hashiwokakero < tablero.txt <ENTER>

o también usando pipe (tubería)

C:\> type tablero.txt | java hashiwokakero<ENTER>

y saca por la salida estándar el tablero solución o una indicación de que no hay solución
si ese es el caso.


Descripción y justificación del tipo de Algoritmo Utilizado
----------------------------------------------------------
Puesto que el tomar la decisión de tender un puente afecta tanto a las celdas vecinas como a otras
celdas distantes, vemos que el problema no puede ser descompuesto en otros de menor tamaño, por
lo que no puede usarse el esquema de Divide y Vencerás.
La resolución a mano de algún hashiwokakero sencillo pronto nos hace comprender que se trata de
un juego de ensayo y error en el que no vasta aplicar una serie de reglas ciegas como en el caso del
esquema Voraz ya hay que estar muy atento al contexto del problema.
Esto, unido a que nos encontramos ante un grafo en el que solo hace falta encontrar una única
solución y no hay condiciones de optimización, nos induce a pensar que el enfoque correcto para
este problema es el del algoritmo de Vuelta Atrás.

Glosario de términos
-------------------
– Hashi: Los objetos de esta clase representan la situación del tablero en un momento dado
mediante un par de conjuntos de puentes (verticales y horizontales) y un par de listas,
ListaDeIslas y listaDeCandidatas.
– Isla: Objeto con los atributos fila, columna y valor que representa a uno de los valores
numéricos de la cuadrícula.
– ListaDeIslas: Es un atributo de la clase Hashi que tiene forma de ArrayList<Isla> donde se
guardan las islas del Hashi cuyo valor sea distinto de cero. El algoritmo de resolución
disminuye en uno el valor de las islas que acaban de formar un puente y va quitando de la
ListaDeIslas a las islas cuyo valor llega a cero, así cuando esla ListaDeIslas llega a cero se
ha llegado a una solución del Hashi.
– Las islas "Agotadas" son las que han tendido todos sus puentes y ya no aparecen en la
ListaDeIslas del Hashi.
– Las islas vecinas a la isla considerada son con las que se está en condiciones de tender
algun puente a partir de la isla dada.
– Isla candidata: Una isla que ya tiene tendido algún puente y es susceptible de tender
alguno más. El algoritmo de resolución utilizado va tendiendo puentes que salen de la lista
de islas candidatas, favoreciendo así la formación de la única región conexa que nos exige el
enunciado del problema.
– Isla afortunada: Entiendo por "Isla Afortuanada" a aquella a la que le es indiferente la
direccion que tome su proximo puente, tiende todos sus puentes hacia las vecinas de manera
forzada. Esta rodeada por una serie de vecinas y a todas tiene que tender al menos un
puente.
– Entiendo por "Isla semi Afortuanada" a aquella a la que le es indiferente la direcciones
que tomen sus puentes, menos el último , que queda indefinido.
Se da en el caso de islas con valor impar
P.ej. una isla de valor 3 rodeada por dos vecinas, una de valor 5 rodeada por 3 vecinas y una
de valor 7 rodeada por 4 vecinas
– Puente: Objeto que representa el puente tendido entre dos islas, sus atributos son las islas
implicadas, el tipo de puente (simple o doble), su orientación (horizontal o vertical) y un par
de arrays donde guardamos las filas y las columnas por las que atraviesa el puente.

Estrategias locales consideradas y condiciones de poda
------------------------------------------------------

– inicialmenteValido() : Para que un hashi sea inicialmente valido el valor acumulado de sus
islas en la lista ha de ser par.
– parcialmenteValido(): Un Hashi sera parcialmente valido cuando:
El valor de una isla (el num de puentes a tender a partir de una isla) sea menor o igual
al num de puentes posibles con sus vecinas a partir de esa isla en el momento actual.
Con esto evito situaciones del tipo : (2)--(2) y (1)--(3)--(1) donde el número de puentes
que se pueden tender es menor al necesario para dejar a cero los valores de las islas.
– PotencialmenteConexo(): Un hashi es potencialmenteConexo cuando las islas que ya no
están en la lista de islas (las islas "Agotadas" que han tendido todos sus puentes) PUEDEN
SER conectadas mediante algún camino a alguna isla que aún está en la lista de Islas. De no
ser así se estarían formando regiones inconexas, incompatibles con la solución correcta del
problema. Utilizo un recorrido en anchura basado en la estructura de datos de cola para
implementar este método.
4- Heurísticas
- La clase Tratamiento: Esta clase se encarga de tratar los Hashis realizando sucesivas
pasadas en busca de islas afortunadas y
semi afortunadas para tender los puentes de las mismas y devolver al hashi ya tratado.
Esta clase se utiliza en dos contextos:
1- Con el método "generarHashiInicial" ,al iniciar el programa para realizar
el pretratamiento del tablero inicial en busca de afortunadas antes de pasárselo al
algoritmo de vuelta atras ,
esto puede facilitarle mucho las cosas al vuelta atrás pues puede que este tratamiento
simplifique sobremanera el tablero inicial.
2- Dentro del algoritmo vuelta atrás cuando se genera un ensayo tendiendo un puente
entre las islas se llama seguidamente al
método "tratarHashi" para que compruebe si el nuevo puente ha provocado la creación
de nuevas afortunadas o semiafortunadas.
- En el método islasVecinas ( isla):
1- si la vecina mirada no tiene a su vez vecinas, el puente con la isla original es
obligado y por tanto , para favorecer cuanto antes su formación, lo pongo al principio de
la lista de vecinas
2 - para favorecer la creación de puentes simples frente a los dobles
Si no hay ningún puente previo entre ambas, la isla mirada se pone al
principio de la lista de vecinas.
- En el método tenderPuente(islaA, islaB):
Para evitar la rapida formacion de puentes dobles:
si alguna de estas islas solo puede tender puentes dobles (tiene ya puentes con todas
sus vecinas ) la pongo al final de la lista de candidatas, recolocando la lista de
candidatas.
- En el método recolocaCandidatas():
Para evitar la rapida formacion de puentes dobles:
voy revisando la lista de las candidatas y si alguna de estas islas
solo puede tender puentes dobles (tiene ya puentes con todas
sus vecinas ) la pongo al final de la lista de candidatas.
- En el método compleciones( hashi):
Ordeno la lista de hashis nuevos según la longitud de su lista de canditatas de menor a
mayor, generalmente la accion de poner primero las compleciones mas restrictivas hace
más eficiente la vuelta atras (wikipedia).
-En el método vueltaAtras(hashi, myEntSal, depuracion):
Hago una revisión de los avances del algoritmo cada diez nodos: si no se ha disminuido
significativamente el numero de islas (si no ha disminuido la cota) es que no hay
avances y entonces recomienzo con el siguiente hashi de las compleciones del hashi
inicial. Si por el contrario si se han producido avances, actualizo el valor de la cota ,
disminuyéndolo al numero de islas actual

Análisis del coste
-----------------
En el estudio del coste en los algoritmos de vuelta atrás es difícil dar una solución exacta, ya que
se desconoce el tamaño de lo explorado a priori, (puede que las heurísticas funcionen muy bien en
la mayoria de los casos, pero que no lo hagan en absoluto en algún otro) , así que solo es posible en
la mayoría de los casos dar una cota superior al tamaño de la búsqueda.
Así que ,tal y como se ha construido el programa , se considera que en cada nodo del árbol de
búsqueda se tiene un tablero hashi , y para el tablero selecciono una isla candidata, y a partir de esa
isla tiendo un puente con alguna de sus vecinas .
Dado que una isla tiene un máximo de cuatro vecinas tengo cuatro posibilidades de tender un
puente para una candidata , suponiendo que todas las n islas del hashi fuesen candidatas tendríamos
4n posibilidades, lo que nos da un coste exponencial para el algoritmo, (en el caso peor).
Este factor exponencial querría decir que el problema es irresoluble, en la práctica , para valores
grandes de n.
En general se puede considerar que si β es el índice de ramificación del árbol y γ es el número de
elementos que componen una solución, o lo que es lo mismo, la profundidad del arbol de búsqueda,
vamos en realidad a tener como máximo β γ pasos para alcanzar todas las soluciones. Si además se
introducen condiciones de poda, éstas influyen reduciendo el índice de ramificación. La poda
además dependerá de la profundidad p y del número n de piezas disponibles para completar la
solución. El coeficiente de poda es un valor entre 0 y 1, si el coeficiente de poda es 0, no existen
condiciones de poda y la búsqueda es totalmente ciega, si es 1 no habrá ramificación. La expresión
general sería [ β (1 - ζ(n , p))] γ . En este problema β = 4 , γ = n y ζ es una función que varía entre
0 y β – 1 / β.


