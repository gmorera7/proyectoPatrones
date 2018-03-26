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
public class Biologo implements Observador{

    @Override
    public void update(QuackObservable pato) {
        System.err.println("El biologo se dio cuenta que el pato "+pato+" hizo quack");
    }
    
}
