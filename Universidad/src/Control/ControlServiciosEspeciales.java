/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import aerolinea.Aerolinea;
import vista.PantallaMenuPrincipal;
import vista.PantallaServiciosEspeciales;

/**
 *
 * @author javeriana.edu.co
 */
public class ControlServiciosEspeciales implements IServiciosEspeciales{
    
    private static ControlServiciosEspeciales instance = null;

    protected ControlServiciosEspeciales() {

    }

    public static ControlServiciosEspeciales getInstance() {
        if (instance == null) {
            instance = new ControlServiciosEspeciales();
            Aerolinea.getInstance().addObserver(PantallaServiciosEspeciales.getInstance());
        }
        return instance;
    }

    @Override
    public void consultarRutaPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
