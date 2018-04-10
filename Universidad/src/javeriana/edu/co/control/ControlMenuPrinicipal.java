package javeriana.edu.co.control;

import javeriana.edu.co.modelo.aerolinea.AccionRutas;
import javeriana.edu.co.modelo.aerolinea.Aerolinea;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlMenuPrinicipal implements IControlMenuPrincipal {

    private static AccionRutas aerolinea;

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
