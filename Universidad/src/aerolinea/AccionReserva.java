package aerolinea;

import reserva.Reserva;

/**
 *
 * @author javeriana.edu.co
 */
public interface AccionReserva {

    public void hacerReserva(Reserva reserva);

    public void buscarReserva(Integer idReserva);
    
    public void consultarReservasPorVueloCheckFood(String numeroVuelo);
    
    public void consultarReservasPorVueloCheckOut(String numeroVuelo);
    
        

}
