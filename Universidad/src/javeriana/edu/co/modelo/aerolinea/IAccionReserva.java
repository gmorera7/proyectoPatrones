package javeriana.edu.co.modelo.aerolinea;

import javeriana.edu.co.modelo.encuesta.Encuesta;
import javeriana.edu.co.modelo.reserva.Reserva;

/**
 *
 * @author javeriana.edu.co
 */
public interface IAccionReserva {

    public void hacerReserva(Reserva reserva);

    public void buscarReserva(Integer idReserva);

    public void consultarReservasPorVueloCheckFood(String numeroVuelo);

    public void consultarReservasPorVueloCheckOut(String numeroVuelo);

    public void cargarMenuComidaEspecial();

    public void cargarMenuTipoComida();

    public void cargarNumeroSillas();

    public void cargarTiposDocumento();
    
    public void hacerEncuesta(Encuesta encuesta);

}
