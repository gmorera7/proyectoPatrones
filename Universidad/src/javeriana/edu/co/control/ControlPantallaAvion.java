package javeriana.edu.co.control;

import javeriana.edu.co.reportes.Reporte;
import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.notificacion.INotificacion;
import javeriana.edu.co.notificacion.Notificacion;
import javeriana.edu.co.modelo.aerolinea.IAccionReserva;
import javeriana.edu.co.modelo.check.IHacerCheck;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlPantallaAvion implements IControlPantallaAvion {

    private static ControlPantallaAvion instance = null;
    private static IAccionReserva aerolinea = Aerolinea.getInstance();
    private INotificacion notificacion = Notificacion.getInstance();
    private IHacerCheck reporte = Reporte.getInstance();

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
