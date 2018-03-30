package Control;

import aerolinea.Aerolinea;
import vista.PantallaAvion;

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
        }
        return instance;
    }
    
    @Override
    public void consultarReservasPorVuelo(String numeroVuelo) {
        Aerolinea.getInstance().consultarReservasPorVueloCheckFood(numeroVuelo);
    }
    
    @Override
    public void realizarCheckFood(Integer idReserva) {
        Aerolinea.getInstance().hacerCheckFood(idReserva);
    }
    
}
