
package javeriana.edu.co.control;

/**
 *
 * @author javeriana.edu.co
 */
public interface IControlPantallaAvion {
    
    public void consultarReservasPorVuelo(String numeroVuelo);
    
    public void realizarCheckFood(Integer idReserva, boolean confirmacion);
    
}
