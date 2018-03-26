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
public class Mallard implements HaceSonido{
    
    Observable observable;
    
    public Mallard(){
        observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("quack!");
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
