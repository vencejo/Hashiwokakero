

import java.util.Vector;

public class Cola <Tipo> {

    //private int            inicio;
    //private int            fin;
    private int    size;
    private Vector<Tipo>    elementos;

    public Cola() {
        super();
        elementos = new Vector<Tipo>();
        //inicio = fin = 0;
        size = 0;
    }
   
    public boolean colaVacia () {
        //if ( (fin-inicio)==0) {
        if ( size==0) {
            return true;
        }
        return false;
    }

    public void encolar ( Tipo o ) {
        //elementos.add(fin++, o);
        elementos.add(size++, o);
    }
    public void vaciarCola(){
        elementos.clear();
        size = 0;
    }

    public Tipo desencolar () {
         Tipo   retorno;
       
        try {
            if(colaVacia())
                throw new ErrorColaVacia();
            else {
                //return elementos.get(inicio++);
                retorno = elementos.get(0);
                elementos.remove(0);
                size--;
                return retorno;
            }
        } catch(ErrorColaVacia error) {
            System.out.println("ERROR: la cola esta vacía");
            return null;
        }
    }

    /*
    public int getFin() {
        return fin;
    }

    public int getInicio() {
        return inicio;
    }
    */
    public int getSize() {
        //return (fin-inicio);
        return (size);
    }
}

@SuppressWarnings("serial")
class ErrorColaVacia extends Exception {
    public ErrorColaVacia() {
        super();
    }
}