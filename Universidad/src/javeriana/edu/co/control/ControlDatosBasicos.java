package javeriana.edu.co.control;

import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.vista.PantallaDatosBasicos;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlDatosBasicos implements IControlDatosBasicos {

    private static ControlDatosBasicos instance = null;

    protected ControlDatosBasicos() {

    }

    public static ControlDatosBasicos getInstance() {
        if (instance == null) {
            instance = new ControlDatosBasicos();
            Aerolinea.getInstance().addObserver(PantallaDatosBasicos.getInstance());
        }
        return instance;
    }

    @Override
    public void cargarTiposDocumento() {
        Aerolinea.getInstance().cargarTiposDocumento();
    }

}
