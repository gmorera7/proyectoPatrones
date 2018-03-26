/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Observable;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Administrador
 */
public class Stock implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        System.err.println("objeto" + arg);
        System.err.println("stock : le doy de baja");
    }
    
}
