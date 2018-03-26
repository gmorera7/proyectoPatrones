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
public class Donald implements HaceSonido{
    
    Observable observable;
    
     public Donald(Observable observable){
         observable = new Observable(this);
    }
     

    @Override
    public void quack() {
        System.err.println("Qghh");
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
