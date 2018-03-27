package Control;

import aerolinea.Aerolinea;
import reserva.Reserva;
import vista.PantallaServiciosEspeciales;

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
}
