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
public class FabricaPatos extends FabricaPatosAbstracta {
    public HaceSonido crearMallard() {
        return new Mallard();
    }
    public HaceSonido crearDonald() {
        return new Donald();
    }
    public HaceSonido crearHule() {
        return new Hule();
    }
}
