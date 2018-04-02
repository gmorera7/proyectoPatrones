package javeriana.edu.co.control;

import javeriana.edu.co.reportes.Reporte;
import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.vista.PantallaCheckIn;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlCheckIn implements IControlCheckIn {

    private static ControlCheckIn instance = null;

    protected ControlCheckIn() {

    }

    public static ControlCheckIn getInstance() {
        if (instance == null) {
            instance = new ControlCheckIn();
            Aerolinea.getInstance().addObserver(PantallaCheckIn.getInstance());
            Aerolinea.getInstance().addObserver(Reporte.getInstance());
        }
        return instance;
    }

    @Override
    public void consultarReserva(Integer id) {
        Aerolinea.getInstance().buscarReserva(id);
    }

    @Override
    public void hacerCheckIn(Integer idRserva, boolean confirmacion) {
        Reporte.getInstance().hacerCheckIn(idRserva,confirmacion);
    }
}
