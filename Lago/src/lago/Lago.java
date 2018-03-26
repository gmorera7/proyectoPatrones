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
public class Lago {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Lago lago = new Lago();
        FabricaPatosAbstracta fabricaPatos = new FabricaConteoQuacks();
        lago.simular(fabricaPatos);
    }
     
    void simular(FabricaPatosAbstracta fabricaPatos) {
        HaceSonido mallard = fabricaPatos.crearMallard();
        HaceSonido hule = fabricaPatos.crearHule();
        HaceSonido donald = fabricaPatos.crearDonald();
        HaceSonido ganzo = new GanzoAdaptador(new Ganzo());
        System.out.println("Mientras tanto en el Lago:");

        Bandada bandada = new Bandada();
        bandada.add(mallard);
        bandada.add(hule);
        bandada.add(donald);
        bandada.add(ganzo);
        
        Biologo biologo = new Biologo();
        mallard.registrarObservador(biologo);
        //bandada.registrarObservador(biologo);
        //bandada.registrarObservador(biologo);
        
        simular(mallard);
        
        //simular(ganzo);
        
        System.out.println("Se han escuchado " + ConteoQuacks.getQuacks() + " quacks");
    }
    
    void simular(HaceSonido pato) {
        pato.quack();
    }
}
