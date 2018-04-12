/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javeriana.edu.co.modelo.comida;

/**
 *
 * @author gerson
 */
public interface IFabricaComidaEspecialAbstracta {

    public Comida comidaVetariana();

    public Comida comidaMar();

    public Comida comidaDiabeticos();

    public Comida comidaBajaEnGrasa();

    public Comida comidaBajaColesterol();

    public Comida comidaBajaProteinas();

    public Comida comidaBajaCalorias();

    public Comida comidaLibreLactosa();

    public Comida comidaParaNinos();

    public Comida crearComidaPorDescripcion(String descripcion);

}
