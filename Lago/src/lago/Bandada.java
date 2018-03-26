/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lago;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author gsarria
 */
public class Bandada implements HaceSonido {
    ArrayList quackers = new ArrayList();
    Observable observable;
    
    public Bandada() {
        observable = new Observable(this);
    }
    
    public void add(HaceSonido quacker) {
        quackers.add(quacker);
    }
    
    public void quack() {
        Iterator iterador = quackers.iterator();
        while(iterador.hasNext()) {
            HaceSonido quacker = (HaceSonido)iterador.next();
            quacker.quack();
        }
    }
    
    public void notificarObservadores() {
        Iterator iterador = quackers.iterator();
        while(iterador.hasNext()) {
            HaceSonido quacker = (HaceSonido)iterador.next();
            quacker.notificarObservadores();
        }
    }
    
    public void registrarObservador(Observador observador) {
        Iterator iterador = quackers.iterator();
        while(iterador.hasNext()) {
            HaceSonido quacker = (HaceSonido)iterador.next();
            quacker.registrarObservador(observador);
        }
    }
}
