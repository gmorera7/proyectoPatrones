/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Observable;

/**
 *
 * @author Administrador
 */
public class Biblioteca {
    
    public void devolverLibro(Libro libro){
        if(libro.getEstado().equals("MALO")){
            AlarmaLibro a = new AlarmaLibro();
            a.addObserver(new Compras());
            a.addObserver(new Administracion());
            a.addObserver(new Stock());
            a.dispararAlarma(libro);
        }
        
    }

    
}
