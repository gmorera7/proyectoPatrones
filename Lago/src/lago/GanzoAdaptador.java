/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lago;

/**
 *
 * @author gsarria
 */
public class GanzoAdaptador implements HaceSonido {
    Ganzo ganzo;
    Observable observable;
    
    public GanzoAdaptador(Ganzo ganzo) {
        this.ganzo = ganzo;
        observable = new Observable(this);
    }
    
    public void quack() {
        ganzo.grazna();
    }
    
    public void notificarObservadores() {
        observable.notificarObservadores();
    }
    
    public void registrarObservador(Observador observador) {
        observable.registrarObservador(observador);
    }
}
