package Control;

import aerolinea.Aerolinea;
import vista.PantallaCheckOut;

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
        }
        return instance;
    }

    @Override
    public void consultarReservasPorVuelo(String numeroVuelo) {
        Aerolinea.getInstance().consultarReservasPorVueloCheckOut(numeroVuelo);
    }

    @Override
    public void realizarCheckOut(Integer idReserva) {
        Aerolinea.getInstance().hacerCheckOut(idReserva);
    }

}
