package javeriana.edu.co.control;

import java.util.Date;

/**
 *
 * @author javeriana.edu.co
 */
public interface IControlReportes {
    
    public void reporte1(Date fechaInicial, Date fechaFinal);
    public void reporte2(Date fechaInicial, Date fechaFinal);
    public void reporte3(Date fechaInicial, Date fechaFinal);
    public void reporte4(Date fechaInicial, Date fechaFinal);
    public void reporte5(Date fechaInicial, Date fechaFinal);
    public void reporte6(Date fechaInicial, Date fechaFinal);
    public void registrarEncuesta(Integer idEncuesta,String calificacion);
    
}
