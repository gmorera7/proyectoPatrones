package javeriana.edu.co.control;

import javeriana.edu.co.reportes.Reporte;
import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.vista.PantallaCheckOut;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlCheckOut implements IControlCheckOut {

    private static ControlCheckOut instance = null;

    protected ControlCheckOut() {

    }

    public static ControlCheckOut getInstance() {
        if (instance == null) {
            instance = new ControlCheckOut();
            Aerolinea.getInstance().addObserver(PantallaCheckOut.getInstance());
            Aerolinea.getInstance().addObserver(Reporte.getInstance());
        }
        return instance;
    }

    @Override
    public void consultarReservasPorVuelo(String numeroVuelo) {
        Aerolinea.getInstance().consultarReservasPorVueloCheckOut(numeroVuelo);
    }

    @Override
    public void realizarCheckOut(Integer idReserva, boolean confirmacion) {
        Reporte.getInstance().hacerCheckOut(idReserva, confirmacion);
    }

}
