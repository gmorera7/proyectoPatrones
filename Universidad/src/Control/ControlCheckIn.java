package Control;

import aerolinea.Aerolinea;
import vista.PantallaCheckIn;

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
        }
        return instance;
    }

    @Override
    public void consultarReserva(Integer id) {
        Aerolinea.getInstance().buscarReserva(id);
    }

    @Override
    public void hacerCheckIn(Integer idRserva) {
        Aerolinea.getInstance().hacerCheckIn(idRserva);
    }
}
