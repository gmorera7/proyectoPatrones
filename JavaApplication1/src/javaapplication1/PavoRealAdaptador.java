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
public class PavoRealAdaptador implements HaceSonido{
     PavoReal pagoReal;
     
         
    public PavoRealAdaptador(PavoReal pagoReal){
        this.pagoReal = pagoReal;
    }
    
    public void quack(){
        pagoReal.guglutear();
    }

    @Override
    public void registrarObservador(Observador observador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notificarObservadores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
