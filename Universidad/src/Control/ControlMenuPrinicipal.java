package Control;

import aerolinea.Aerolinea;
import vista.PantallaMenuPrincipal;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlMenuPrinicipal implements IControlMenuPrincipal {

    private static ControlMenuPrinicipal instance = null;

    protected ControlMenuPrinicipal() {

    }

    public static ControlMenuPrinicipal getInstance() {
        if (instance == null) {
            instance = new ControlMenuPrinicipal();
            Aerolinea.getInstance().addObserver(PantallaMenuPrincipal.getInstance());
        }
        return instance;
    }

    @Override
    public void cargarCatalogosCiudadesOrigen() {
        Aerolinea.getInstance().cargarCiudadesOrigen();
    }

    @Override
    public void cargarCatalogosCiudadesDestino() {
        Aerolinea.getInstance().cargarCiudadesDestino();
    }

}
