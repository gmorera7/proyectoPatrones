package Control;

import reserva.Reserva;

/**
 *
 * @author javeriana.edu.co
 */
public interface IServiciosEspeciales {
    
    public void consultarRutaPorId(Integer id);
    
    public void crearReserva(Reserva reserva);
    
}
