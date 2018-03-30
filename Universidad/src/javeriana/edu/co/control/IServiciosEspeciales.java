package javeriana.edu.co.control;

import javeriana.edu.co.modelo.reserva.Reserva;

/**
 *
 * @author javeriana.edu.co
 */
public interface IServiciosEspeciales {
    
    public void consultarRutaPorId(Integer id);
    
    public void crearReserva(Reserva reserva);
    
    public void cargarMenuComidaEspecial();
    
    public void cargarMenuTipoComida();
    
    public void cargarNumeroSillas();
    
}
