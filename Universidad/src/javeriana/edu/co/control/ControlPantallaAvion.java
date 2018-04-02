package javeriana.edu.co.control;

import javeriana.edu.co.reportes.Reporte;
import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.notificacion.Notificacion;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlPantallaAvion implements IControlPantallaAvion {

    private static ControlPantallaAvion instance = null;
    private static Aerolinea aerolinea = Aerolinea.getInstance();

    protected ControlPantallaAvion() {

    }

    public static ControlPantallaAvion getInstance() {
        if (instance == null) {
            instance = new ControlPantallaAvion();
            aerolinea.addObserver(Reporte.getInstance());
            aerolinea.addObserver(Notificacion.getInstance());
        }
        return instance;
    }

    @Override
    public void consultarReservasPorVuelo(String numeroVuelo) {
        aerolinea.consultarReservasPorVueloCheckFood(numeroVuelo);
    }

    @Override
    public void realizarCheckFood(Integer idReserva, boolean confirmacion) {
        Reporte.getInstance().hacerCheckFood(idReserva, confirmacion);
        Notificacion.getInstance().notificarEmail(idReserva);
    }

}
