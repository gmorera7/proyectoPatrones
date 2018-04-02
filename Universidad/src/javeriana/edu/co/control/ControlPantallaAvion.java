package javeriana.edu.co.control;

import javeriana.edu.co.reportes.Reporte;
import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.notificacion.Notificacion;
import javeriana.edu.co.vista.PantallaAvion;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlPantallaAvion implements IControlPantallaAvion {
    
    private static ControlPantallaAvion instance = null;
    
    protected ControlPantallaAvion() {
        
    }
    
    public static ControlPantallaAvion getInstance() {
        if (instance == null) {
            instance = new ControlPantallaAvion();
            Aerolinea.getInstance().addObserver(PantallaAvion.getInstance());            
            Aerolinea.getInstance().addObserver(Reporte.getInstance());
            Aerolinea.getInstance().addObserver(Notificacion.getInstance());
        }
        return instance;
    }
    
    @Override
    public void consultarReservasPorVuelo(String numeroVuelo) {
        Aerolinea.getInstance().consultarReservasPorVueloCheckFood(numeroVuelo);
    }
    
    @Override
    public void realizarCheckFood(Integer idReserva, boolean confirmacion) {
        Reporte.getInstance().hacerCheckFood(idReserva,confirmacion);
        Notificacion.getInstance().notificarEmail(idReserva);
    }
    
}
