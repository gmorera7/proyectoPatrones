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
public class Biologo implements Observador {
    public void update(QuackObservable pato) {
        System.out.println("El biologo se dio cuenta que " + pato + " hizo quack");
    }
}
