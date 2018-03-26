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
public class FabricaConteoPatos extends FabricaPatosAbstracta{
    
    Observable observable;

    public FabricaConteoPatos(){
        
    }
    @Override
    public HaceSonido crearMallard() {
        return new ConteoQuacks(new Mallard());
    }

    @Override
    public HaceSonido crearDonald() {
        return new ConteoQuacks(new Donald(observable));
    }

    @Override
    public HaceSonido crearHule() {
        return new ConteoQuacks(new Hule());
        
    }
   
    
    
}
