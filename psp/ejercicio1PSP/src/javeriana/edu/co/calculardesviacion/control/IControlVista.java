/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javeriana.edu.co.calculardesviacion.control;

/**
 *
 * @author gersonmorera
 */
public interface IControlVista {
    
    public void adicionarElementoMemoria(Double numero);
    
    public Double calcularMediaAritmetica();
    
    public Double calcularDesviacionEstandar(Double mediaAritmetica);
    
}
