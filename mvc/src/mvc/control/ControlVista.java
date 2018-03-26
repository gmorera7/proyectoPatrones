/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.control;

import mvc.Vista;
import mvc.modelo.Modelo;

/**
 *
 * @author Administrador
 */
public class ControlVista{
    
    private Vista vista ;
        
    public ControlVista(Vista vista){
        this.vista = vista; 
    }

    public void solicitudSaludar(String nombre) {
        Modelo modelo = new Modelo();
        modelo.addObserver(vista);
         modelo.holaMundo(nombre);
    }
}


