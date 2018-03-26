package Control;

import aerolinea.Aerolinea;
import busqueda.Busqueda;
import vista.PantallaMenuPrincipal;
import vista.PantallaSeleccionVuelo;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlSeleccionVuelo implements IControlSeleccionVuelo {

    private static ControlSeleccionVuelo instance = null;

    protected ControlSeleccionVuelo() {

    }

    public static ControlSeleccionVuelo getInstance() {
        if (instance == null) {
            instance = new ControlSeleccionVuelo();
            Aerolinea.getInstance().addObserver(PantallaSeleccionVuelo.getInstance());
        }
        return instance;
    }

    @Override
    public void buscarRutas(Busqueda busqueda) {
        Aerolinea.getInstance().addObserver(PantallaMenuPrincipal.getInstance());
        Aerolinea.getInstance().buscarRuta(busqueda);
    }
}
