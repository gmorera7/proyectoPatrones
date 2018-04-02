package javeriana.edu.co.control;

/**
 *
 * @author javeriana.edu.co
 */
public interface IControlCheckOut {

    public void consultarReservasPorVuelo(String numeroVuelo);

    public void realizarCheckOut(Integer idReserva, boolean confirmacion);

}
