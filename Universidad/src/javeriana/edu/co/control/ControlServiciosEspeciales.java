package javeriana.edu.co.control;

import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.modelo.reserva.Reserva;
import javeriana.edu.co.vista.PantallaServiciosEspeciales;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlServiciosEspeciales implements IServiciosEspeciales {

    private static ControlServiciosEspeciales instance = null;

    protected ControlServiciosEspeciales() {

    }

    public static ControlServiciosEspeciales getInstance() {
        if (instance == null) {
            instance = new ControlServiciosEspeciales();
            Aerolinea.getInstance().addObserver(PantallaServiciosEspeciales.getInstance());
        }
        return instance;
    }

    @Override
    public void consultarRutaPorId(Integer id) {
        Aerolinea.getInstance().consultarRutaPorId(id);
    }

    @Override
    public void crearReserva(Reserva reserva) {
        Aerolinea.getInstance().hacerReserva(reserva);
    }

    @Override
    public void cargarMenuComidaEspecial() {
        Aerolinea.getInstance().cargarMenuComidaEspecial();
    }

    @Override
    public void cargarMenuTipoComida() {
        Aerolinea.getInstance().cargarMenuTipoComida();
    }

    @Override
    public void cargarNumeroSillas() {
        Aerolinea.getInstance().cargarNumeroSillas();
    }
}
