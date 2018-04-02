package javeriana.edu.co.control;

import javeriana.edu.co.reportes.Reporte;
import java.util.Date;
import javeriana.edu.co.reportes.HacerReporte;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlReportes implements IControlReportes {

    private static ControlReportes instance = null;

    private HacerReporte reporte = Reporte.getInstance();

    protected ControlReportes() {

    }

    public static ControlReportes getInstance() {
        if (instance == null) {
            instance = new ControlReportes();
        }
        return instance;
    }

    @Override
    public void reporte1(Date fechaInicial, Date fechaFinal) {
        reporte.reporte1(fechaInicial, fechaFinal);
    }

    @Override
    public void reporte2(Date fechaInicial, Date fechaFinal) {
        reporte.reporte2(fechaInicial, fechaFinal);
    }

    @Override
    public void reporte3(Date fechaInicial, Date fechaFinal) {
        reporte.reporte3(fechaInicial, fechaFinal);

    }

    @Override
    public void reporte4(Date fechaInicial, Date fechaFinal) {
        reporte.reporte4(fechaInicial, fechaFinal);
    }

    @Override
    public void reporte5(Date fechaInicial, Date fechaFinal) {
        reporte.reporte5(fechaInicial, fechaFinal);
    }

    @Override
    public void reporte6(Date fechaInicial, Date fechaFinal) {
        reporte.reporte6(fechaInicial, fechaFinal);

    }

    @Override
    public void registrarEncuesta(Integer idEncuesta, String calificacion) {
        reporte.hacerEncuesta(idEncuesta, calificacion);
    }

}
