package javeriana.edu.co.control;

import java.util.Date;
import javeriana.edu.co.reportes.HacerReporte;
import javeriana.edu.co.reportes.Reporte;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlPantallaAbastecedor implements IControlPantallaAbastecedor {

    private static ControlPantallaAbastecedor instance = null;

    private HacerReporte reporte = Reporte.getInstance();

    protected ControlPantallaAbastecedor() {

    }

    public static ControlPantallaAbastecedor getInstance() {
        if (instance == null) {
            instance = new ControlPantallaAbastecedor();
        }
        return instance;
    }

    @Override
    public void reporte6(Date fechaInicial, Date FechaFinal) {
        reporte.reporte6(fechaInicial, FechaFinal);
    }

}
