package javeriana.edu.co.control;

import java.util.Date;
import javeriana.edu.co.reportes.Reporte;
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
            Reporte.getInstance().addObserver(PantallaAbastecedor.getInstance());
        }
        return instance;
    }

    @Override
    public void reporte6(Date fechaInicial,Date FechaFinal) {
        Reporte.getInstance().reporte6(fechaInicial, FechaFinal);
    }

}
