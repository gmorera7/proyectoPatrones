/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javeriana.edu.co.modelo.usuario;

/**
 *
 * @author javeriana.edu.co
 */
public interface IFabricaPersonaAbstracta {

    public Persona crearPasajero(String tipoDcumento, String numeroDocumento, String primerN, String segundoN, String primerA, String segundoA, String direccion, String correo, String telefono);

    public Persona crearAzafata();

    public Persona crearAnalistaReporte();

    public Persona crearAbastecedor();

}
