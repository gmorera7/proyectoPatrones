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
public class Observable implements QuackObservable {
    ArrayList observadores = new ArrayList();
    QuackObservable pato;
    
    public Observable(QuackObservable pato) {
        this.pato = pato;
    }

    @Override
    public void registrarObservador(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void notificarObservadores() {
        Iterator iterador = observadores.iterator();
        while(iterador.hasNext()) {
            Observador observador = (Observador)iterador.next();
            observador.update(pato);
        }
    }
    
    
}
