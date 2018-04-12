package javeriana.edu.co.control;

import javeriana.edu.co.reportes.Reporte;
import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.modelo.aerolinea.IAccionReserva;
import javeriana.edu.co.modelo.check.IHacerCheck;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlCheckIn implements IControlCheckIn {

    private static ControlCheckIn instance = null;
    private static IHacerCheck reporteCheckin = Reporte.getInstance();
    private static IAccionReserva aerolinea = Aerolinea.getInstance();

    protected ControlCheckIn() {
    }

    public static ControlCheckIn getInstance() {
        if (instance == null) {
            instance = new ControlCheckIn();
        }
        return instance;
    }

    @Override
    public void consultarReserva(Integer id) {
        aerolinea.buscarReserva(id);
    }

    @Override
    public void hacerCheckIn(Integer idRserva, boolean confirmacion) {
        reporteCheckin.hacerCheckIn(idRserva, confirmacion);
    }
}
