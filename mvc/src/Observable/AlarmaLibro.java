/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Observable;

import java.util.Observable;

/**
 *
 * @author Administrador
 */
public class AlarmaLibro extends Observable{
    
     public void dispararAlarma(Libro libro){
         setChanged();
         notifyObservers("rompieron el libro ++++++++++++: "+libro.getTitulo());
         
     }
    
}
