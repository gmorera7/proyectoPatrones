/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.Scanner;

/**
 *
 * @author gerson
 */
public class Consola implements IConsola {

    private static Consola instance = null;
    Scanner sn = new Scanner(System.in);

    protected Consola() {
        // Exists only to defeat instantiation.
    }

    public static Consola getInstance() {
        if (instance == null) {
            instance = new Consola();
        }
        return instance;
    }

    @Override
    public int perdirRuta() {
        System.out.println("Seleccione el id de la ruta: ");
        int ruta = sn.nextInt();
        return ruta;
    }

    @Override
    public int menuPrincipal() {
        System.out.println("1. Reservar Viaje");
        System.out.println("2. Hacer checkIn");
        System.out.println("3. Opcion 3");
        System.out.println("4. Salir");

        System.out.println("Escribe una de las opciones");
        int opcion = sn.nextInt();
        return opcion;

    }

    @Override
    public int pedirNumeroReserva() {
        System.out.println("Número de reserva : ");
        int numeroReserva = sn.nextInt();
        return numeroReserva;
    }

    @Override
    public int pedirDatosComida() {
        System.out.println("Tipo de comida 1.Especial 2.Regular cual desea: ");
        int opcion = sn.nextInt();
        return opcion;
    }

    @Override
    public String pedirNumeroSilla() {
        System.out.println("Seleccione el numero de silla: ");
        String opcion = sn.next();
        return opcion;
    }
    
    

}
