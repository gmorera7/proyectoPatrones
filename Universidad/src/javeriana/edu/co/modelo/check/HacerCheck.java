package javeriana.edu.co.modelo.check;

/**
 *
 * @author javeriana.edu.co
 */
public interface HacerCheck {

    public void hacerCheckIn(Integer idReserva, boolean confirmacion);

    public void hacerCheckOut(Integer idReserva, boolean confirmacion);

    public void hacerCheckFood(Integer idReserva, boolean confirmacion);

}
