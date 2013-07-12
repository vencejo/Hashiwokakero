import java.util.HashSet;
/**
 * Esta clase implementa un conjunto del tipo HashSet<Puente> con los puentes
 */
public class Conjuto_Puentes
{
    // instance variables - replace the example below with your own
    public HashSet<Puente> conjuntoPuentes;

    /**
     * Constructor for objects of class Conjuto_Puentes
     */
    public Conjuto_Puentes()
    {
        // initialise instance variables
        conjuntoPuentes = new HashSet<Puente>();
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void meterPuente(Puente puente)
    {
        // put your code here
        conjuntoPuentes.add(puente);
    }
}
