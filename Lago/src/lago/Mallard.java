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
public class Mallard implements HaceSonido {
    Observable observable;
    
    public Mallard() {
        observable = new Observable(this);
    }
    
    @Override
    public void quack() {
        System.out.println("Quack!");
        notificarObservadores();
    }
    
    public void notificarObservadores() {
        observable.notificarObservadores();
    }
    
    public void registrarObservador(Observador observador) {
        observable.registrarObservador(observador);
    }
}
