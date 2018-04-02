package javeriana.edu.co.control;

import javeriana.edu.co.modelo.aerolinea.AccionRutas;
import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.modelo.busqueda.Busqueda;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlSeleccionVuelo implements IControlSeleccionVuelo {

    private static ControlSeleccionVuelo instance = null;

    private AccionRutas aerolinea = Aerolinea.getInstance();

    protected ControlSeleccionVuelo() {

    }

    public static ControlSeleccionVuelo getInstance() {
        if (instance == null) {
            instance = new ControlSeleccionVuelo();

        }
        return instance;
    }

    @Override
    public void buscarRutas(Busqueda busqueda) {
        aerolinea.buscarRuta(busqueda);
    }
}
