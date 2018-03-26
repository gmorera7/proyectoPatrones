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
public class ConteoQuacks implements HaceSonido {
    HaceSonido pato;
    static int numeroQuacks;    
    Observable observable;
    
    public ConteoQuacks(HaceSonido pato) {
        this.pato = pato;
        observable = new Observable(this);
    }
    
    public void quack() {
        pato.quack();
        numeroQuacks++;
    }
    
    public static int getQuacks() {
        return numeroQuacks;
    }
    
    public void notificarObservadores() {
        observable.notificarObservadores();
    }
    
    public void registrarObservador(Observador observador) {
        observable.registrarObservador(observador);
    }
}
