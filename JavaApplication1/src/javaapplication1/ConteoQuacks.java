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
public class ConteoQuacks implements HaceSonido{
    HaceSonido pato;
    GanzoAdaptador ganzo;
    PavoRealAdaptador pavoRealAdaptador;
    
    Observable observable;
    
        
    public ConteoQuacks(Observable observable){
         observable = new Observable(this);
    }
    
    static int numeroQuacks;
    
 
   public  ConteoQuacks(HaceSonido pato){
       this.pato = pato;
   }
   
   public  ConteoQuacks(GanzoAdaptador ganzo){
       this.ganzo = ganzo;
   }
   
   public  ConteoQuacks(PavoRealAdaptador pavoReal){
       this.pavoRealAdaptador = pavoRealAdaptador;
   }
   
    @Override
    public void quack(){
        pato.quack();
      //  ganzo.quack();
      //  pavoRealAdaptador.quack();
        numeroQuacks++;
    }
    public static int getQuacks(){
        return numeroQuacks;
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
