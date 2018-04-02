package javeriana.edu.co.control;

/**
 *
 * @author javeriana.edu.co
 */
public interface IControlCheckIn {

    public void consultarReserva(Integer id);

    public void hacerCheckIn(Integer idReserva, boolean confirmacion);

}
