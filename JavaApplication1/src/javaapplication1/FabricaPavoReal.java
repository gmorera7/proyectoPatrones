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
public class FabricaPavoReal extends FabricaPavoRealAbstracta{

    @Override
    public PavoRealAdaptador creaPavoReal() {
        return  new PavoRealAdaptador(new PavoReal());
    }
    
}
