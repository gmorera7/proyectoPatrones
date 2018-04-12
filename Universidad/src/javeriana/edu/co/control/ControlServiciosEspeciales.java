package javeriana.edu.co.control;

import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.modelo.reserva.Reserva;
import javeriana.edu.co.modelo.aerolinea.IAccionReserva;
import javeriana.edu.co.modelo.aerolinea.IAccionRutas;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlServiciosEspeciales implements IServiciosEspeciales {

    private static ControlServiciosEspeciales instance = null;
    private IAccionReserva reservar = Aerolinea.getInstance();
    private IAccionRutas rutas = Aerolinea.getInstance();

    protected ControlServiciosEspeciales() {
    }

    public static ControlServiciosEspeciales getInstance() {
        if (instance == null) {
            instance = new ControlServiciosEspeciales();
        }
        return instance;
    }

    @Override
    public void consultarRutaPorId(Integer id) {
        rutas.consultarRutaPorId(id);
    }

    @Override
    public void crearReserva(Reserva reserva) {
        reservar.hacerReserva(reserva);
    }

    @Override
    public void cargarMenuComidaEspecial() {
        reservar.cargarMenuComidaEspecial();
    }

    @Override
    public void cargarMenuTipoComida() {
        reservar.cargarMenuTipoComida();
    }

    @Override
    public void cargarNumeroSillas() {
        reservar.cargarNumeroSillas();
    }
}
