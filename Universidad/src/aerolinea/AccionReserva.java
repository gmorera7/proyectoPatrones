package aerolinea;

import reserva.Reserva;

/**
 *
 * @author javeriana.edu.co
 */
public interface AccionReserva {

    public void hacerReserva(Reserva reserva);

    public Reserva buscarReserva(Integer idReserva);

}
