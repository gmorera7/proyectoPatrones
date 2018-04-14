/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javeriana.edu.co.calculardesviacion.control;

import javeriana.edu.co.calculardesviacion.modelo.DesviacionEstandar;
import javeriana.edu.co.calculardesviacion.modelo.Memoria;

/**
 *
 * @author gersonmorera
 */
public class ControlVista implements IControlVista {

    private DesviacionEstandar memoria = Memoria.getInstance();

    private static ControlVista instance = null;

    protected ControlVista() {
    }

    public static ControlVista getInstance() {
        if (instance == null) {
            instance = new ControlVista();
        }
        return instance;
    }

    @Override
    public void adicionarElementoMemoria(Double numero) {
        memoria.adicionarElemento(numero);
    }

    @Override
    public Double calcularMediaAritmetica() {
        return memoria.mediaAritmetica();
    }

    @Override
    public Double calcularDesviacionEstandar(Double mediaAritmetica) {
        return memoria.desviacionEstandar(mediaAritmetica);
    }

}
