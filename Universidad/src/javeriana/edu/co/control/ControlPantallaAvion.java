package javeriana.edu.co.control;

import javeriana.edu.co.modelo.aerolinea.AccionReserva;
import javeriana.edu.co.reportes.Reporte;
import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.modelo.check.HacerCheck;
import javeriana.edu.co.notificacion.INotificacion;
import javeriana.edu.co.notificacion.Notificacion;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlPantallaAvion implements IControlPantallaAvion {

    private static ControlPantallaAvion instance = null;
    private static AccionReserva aerolinea = Aerolinea.getInstance();
    private INotificacion notificacion = Notificacion.getInstance();
    private HacerCheck reporte = Reporte.getInstance();

    protected ControlPantallaAvion() {
    }

    public static ControlPantallaAvion getInstance() {
        if (instance == null) {
            instance = new ControlPantallaAvion();
            Aerolinea.getInstance().addObserver(Reporte.getInstance());
            Aerolinea.getInstance().addObserver(Notificacion.getInstance());
        }
        return instance;
    }

    @Override
    public void consultarReservasPorVuelo(String numeroVuelo) {
        aerolinea.consultarReservasPorVueloCheckFood(numeroVuelo);
    }

    @Override
    public void realizarCheckFood(Integer idReserva, boolean confirmacion) {
        reporte.hacerCheckFood(idReserva, confirmacion);
        notificacion.notificarEmail(idReserva);
    }
}
