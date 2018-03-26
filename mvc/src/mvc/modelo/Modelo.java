/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo;

import java.util.Observable;

/**
 *
 * @author Administrador
 */
public class Modelo extends Observable {

    public Modelo() {

    }

    public void holaMundo(String nombre) {
        setChanged();
        notifyObservers("hola todo bn" + nombre);
    }

}
