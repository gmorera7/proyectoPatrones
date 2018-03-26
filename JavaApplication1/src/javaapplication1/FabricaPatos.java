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
public class FabricaPatos extends FabricaPatosAbstracta{
    
    Observable observable;

    @Override
    public HaceSonido crearMallard() {
        return new Mallard();
    }

    @Override
    public HaceSonido crearDonald() {
        return new Donald(observable);
    }

    @Override
    public HaceSonido crearHule() {
        return new Hule();
    }
    
    
}
