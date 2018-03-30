package javeriana.edu.co.control;

import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.modelo.busqueda.Busqueda;
import javeriana.edu.co.vista.PantallaMenuPrincipal;
import javeriana.edu.co.vista.PantallaSeleccionVuelo;

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
