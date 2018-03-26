/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.ArrayList;
import java.util.Iterator;



/**
 *
 * @author gerson
 */
public class Observable implements QuackObservable{
    
    ArrayList observadores = new ArrayList();
    
    QuackObservable pato;
    
    public Observable(QuackObservable pato){
        this.pato = pato;
    }

    @Override
    public void registrarObservador(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void notificarObservadores() {
        Iterator iterator = observadores.iterator();
        while(iterator.hasNext()){
            Observador observador = (Observador)iterator.next();
            observador.update(pato);
        }
    }
    
    
}
