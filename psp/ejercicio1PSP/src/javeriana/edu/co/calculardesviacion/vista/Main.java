/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javeriana.edu.co.calculardesviacion.vista;

/**
 *
 * @author gersonmorera
 */
public class Main {

    private static final Vista vista = Vista.getInstance();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        vista.solicitarYCalcularDesviacion();
    }

}
