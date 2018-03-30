package javeriana.edu.co.control;

import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.vista.PantallaAbastecedor;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlPantallaAbastecedor implements IControlPantallaAbastecedor {

    private static ControlPantallaAbastecedor instance = null;

    protected ControlPantallaAbastecedor() {

    }

    public static ControlPantallaAbastecedor getInstance() {
        if (instance == null) {
            instance = new ControlPantallaAbastecedor();
            Aerolinea.getInstance().addObserver(PantallaAbastecedor.getInstance());
        }
        return instance;
    }

    @Override
    public void consultarReservasPorVuelo(String numeroVuelo) {
        Aerolinea.getInstance().consultarReservasPorVueloCheckFood(numeroVuelo);
    }

}
