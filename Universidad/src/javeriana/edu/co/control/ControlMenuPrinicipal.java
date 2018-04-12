package javeriana.edu.co.control;

import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.modelo.aerolinea.IAccionRutas;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlMenuPrinicipal implements IControlMenuPrincipal {

    private static IAccionRutas aerolinea;

    private static ControlMenuPrinicipal instance = null;

    protected ControlMenuPrinicipal() {

    }

    public static ControlMenuPrinicipal getInstance() {
        if (instance == null) {
            instance = new ControlMenuPrinicipal();
            aerolinea = Aerolinea.getInstance();
        }
        return instance;
    }

    @Override
    public void cargarCatalogosCiudadesOrigen() {
        aerolinea.cargarCiudadesOrigen();
    }

    @Override
    public void cargarCatalogosCiudadesDestino() {
        aerolinea.cargarCiudadesDestino();
    }
}
