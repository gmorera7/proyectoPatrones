/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javeriana.edu.co.calculardesviacion.modelo;

/**
 *
 * @author gersonmorera
 */
public interface DesviacionEstandar {

    public Double mediaAritmetica();

    public Double desviacionEstandar(Double media);
    
    public void adicionarElemento(Double valor);

}
