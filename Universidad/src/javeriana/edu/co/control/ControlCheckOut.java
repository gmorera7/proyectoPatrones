package javeriana.edu.co.control;

import javeriana.edu.co.reportes.Reporte;
import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.modelo.aerolinea.IAccionReserva;
import javeriana.edu.co.modelo.check.IHacerCheck;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlCheckOut implements IControlCheckOut {

    private static ControlCheckOut instance = null;
    private static IAccionReserva aerolinea = Aerolinea.getInstance();
    private static IHacerCheck reporteCheckOut = Reporte.getInstance();

    protected ControlCheckOut() {
    }

    public static ControlCheckOut getInstance() {
        if (instance == null) {
            instance = new ControlCheckOut();
            Aerolinea.getInstance().addObserver(Reporte.getInstance());
        }
        return instance;
    }

    @Override
    public void consultarReservasPorVuelo(String numeroVuelo) {
        aerolinea.consultarReservasPorVueloCheckOut(numeroVuelo);
    }

    @Override
    public void realizarCheckOut(Integer idReserva, boolean confirmacion) {
        reporteCheckOut.hacerCheckOut(idReserva, confirmacion);
    }
}
