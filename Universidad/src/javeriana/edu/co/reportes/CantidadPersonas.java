/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javeriana.edu.co.reportes;

import javeriana.edu.co.modelo.usuario.Persona;

/**
 *
 * @author gerson
 */
public class CantidadPersonas {

    /**
     * @return the cantidadVeces
     */
    public static int getCantidadVeces() {
        return cantidadVeces;
    }

    /**
     * @param aCantidadVeces the cantidadVeces to set
     */
    public static void setCantidadVeces(int aCantidadVeces) {
        cantidadVeces = aCantidadVeces;
    }
    
    private Persona persona;
    private static int cantidadVeces;

    /**
     * @return the persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    
    
}
