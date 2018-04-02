package javeriana.edu.co.control;

import javeriana.edu.co.modelo.aerolinea.AccionReserva;
import javeriana.edu.co.modelo.aerolinea.Aerolinea;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlDatosBasicos implements IControlDatosBasicos {

    private static ControlDatosBasicos instance = null;
    private AccionReserva aerolinea = Aerolinea.getInstance();

    protected ControlDatosBasicos() {

    }

    public static ControlDatosBasicos getInstance() {
        if (instance == null) {
            instance = new ControlDatosBasicos();
        }
        return instance;
    }

    @Override
    public void cargarTiposDocumento() {
        aerolinea.cargarTiposDocumento();
    }
}
