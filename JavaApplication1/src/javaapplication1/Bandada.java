/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author gerson
 */
public class Bandada implements HaceSonido{
    
    ArrayList quackers = new ArrayList();
    Observable observable;
    
    public Bandada(Observable observable){
         observable = new Observable(this);
    }
    
    public void add(HaceSonido quacker){
        quackers.add(quacker);
    }

    @Override
    public void quack() {
         Iterator iterator = quackers.iterator();
         while(iterator.hasNext()){
             HaceSonido quacker = (HaceSonido)iterator.next();
             quacker.quack();
         }
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
