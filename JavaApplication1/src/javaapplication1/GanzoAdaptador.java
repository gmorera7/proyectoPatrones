/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author gerson
 */
public class GanzoAdaptador implements HaceSonido{
    Ganzo ganzo;
    
    Observable observable;
    
    public GanzoAdaptador(Observable observable){
        observable = new Observable(this);
    }
    
     
    
    public GanzoAdaptador(Ganzo ganzo){
        this.ganzo = ganzo;
    }
    
    public void quack(){
        ganzo.grazna();
    }

    @Override
    public void registrarObservador(Observador observador) {
        observable.registrarObservador(observador);
    }

    @Override
    public void notificarObservadores() {
        observable.notificarObservadores();
    }
    
}
