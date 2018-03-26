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
public class Lago {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Lago lago = new Lago();
        FabricaPatosAbstracta fabricaPatos = new FabricaConteoPatos();
        FabricaPavoRealAbstracta fabricaPavo = new FabricaPavoReal();
        FabricaGanzoAbstracta fabricaGanzo = new FabricaGanzo();
        lago.simular(fabricaPatos, fabricaPavo, fabricaGanzo);

    }

    void simular(FabricaPatosAbstracta fabricaPato, FabricaPavoRealAbstracta fabricaPavo, FabricaGanzoAbstracta fabricaGanzo) {
        HaceSonido mallard = fabricaPato.crearMallard();
        HaceSonido donald = fabricaPato.crearDonald();
        HaceSonido hule = fabricaPato.crearHule();
        HaceSonido ganzo = new GanzoAdaptador(fabricaGanzo.crearGanzo());
        HaceSonido pavoReal = fabricaPavo.creaPavoReal();

        System.out.println("Mientras tanto en el lago:");
       // simular(mallard);
       // simular(donald);
       // simular(hule);
      //  simular(ganzo);
     //   simular(pavoReal);

       
        Observable observable = null ;
        
        Bandada bandada = new Bandada(observable);
        bandada.add(mallard);
        bandada.add(hule);
        bandada.add(donald);
        bandada.add(ganzo);
        bandada.add(pavoReal);
         simular(bandada);
          System.err.println("se han escuchado " + ConteoQuacks.getQuacks());
          
          
            Biologo biologo = new Biologo();
            bandada.registrarObservador(biologo);
            

    }

    void simular(HaceSonido pato) {
        pato.quack();

    }

}
