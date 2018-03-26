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
public class Hule implements HaceSonido {
    Observable observable;
    
    public Hule() {
        observable = new Observable(this);
    }
    
    public void quack() {
        System.out.println("Gni!");
        notificarObservadores();
    }
    
    public void notificarObservadores() {
        observable.notificarObservadores();
    }
    
    public void registrarObservador(Observador observador) {
        observable.registrarObservador(observador);
    }
}
