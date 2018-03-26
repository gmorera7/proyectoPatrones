/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad;

import Usuario.FabricaPersona;
import Usuario.Pasajero;
import aerolinea.Aerolinea;
import check.CheckIn;
import comida.ComidaEspecial;
import comida.ComidaRegular;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import vista.Consola;
import reserva.Reserva;
import reserva.Ruta;

/**
 *
 * @author javeriana.edu.co
 */
public class Universidad {

    static Aerolinea aerolinea = Aerolinea.getInstance();
    static int opcion, trayectoReserva, tipoComida;
    static String numeroSilla;

    public static void main(String[] args) {
        Reserva reserva = new Reserva();
        reserva.setId(1);

        boolean salir = false;

        aerolinea.cargarRutas();

        while (!salir) {
            opcion = Consola.getInstance().menuPrincipal();
            switch (opcion) {
                case 1:
                    //aerolinea.imprimirRutas();
                    trayectoReserva = Consola.getInstance().perdirRuta();
              //      reserva.setRuta(aerolinea.buscarRuta(trayectoReserva));
                    
                    reserva = pidaDatosPasajero(reserva);

                    tipoComida = Consola.getInstance().pedirDatosComida();
                    reserva.setComida(tipoComida == 2 ? new ComidaRegular() : new ComidaEspecial());

                    numeroSilla = Consola.getInstance().pedirNumeroSilla();
                    reserva.setNumeroSilla(numeroSilla);
                    
                    aerolinea.hacerReserva(reserva);
                    break;
                case 2:
                    int numeroReserva = Consola.getInstance().pedirNumeroReserva();
                    aerolinea.hacerCheckIn(numeroReserva);
                    break;
                case 3:

                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Solo números entre 1 y 4");
            }
        }
    }

    public static void realizarCheckIn() {
        Scanner sn = new Scanner(System.in);
        System.out.println("Número de la reserva: ");
        int opcion = sn.nextInt();

        CheckIn checkIn = new CheckIn();
        checkIn.setConfirmacion("ok");
        checkIn.setId(1);
        //    reserva.setCheck(checkIn);

    }

    public static Reserva pidaDatosPasajero(Reserva reserva) {

        Pasajero pasajero = FabricaPersona.getInstance().crearPasajero();
        pasajero.setPrimerNombre("GERSON");
        pasajero.setSegundoNombre("");
        pasajero.setPrimerApellido("MORERA");
        pasajero.setSegundoApellido("RESTREPO");
        pasajero.setTipoDocumento("1");
        pasajero.setNumeroDocumento("1104458524");
        pasajero.setCorreoElectronico("a@hotmail.com");
        pasajero.setTelefono("3002869650");
        pasajero.setDireccion("calle 14 84-65");
        reserva.setPersona(pasajero);
        return reserva;
    }

    public static void reservarViaje(Reserva reserva) {
        /*
        Scanner sn = new Scanner(System.in);
        ArrayList rutas = aerolinea.getRutas();
        Ruta rutaReserva = null;

        for (Iterator iterator = rutas.iterator(); iterator.hasNext();) {
            Ruta ruta = (Ruta) iterator.next();
            System.err.println("ruta:  " + ruta.getId() + " Origen: " + ruta.getOrigen() + " Detino " + ruta.getDestino());
        }
        System.out.println("Seleccione el id de la ruta: ");
        int trayectoReserva = sn.nextInt();

        for (Iterator iterator = rutas.iterator(); iterator.hasNext();) {
            Ruta ruta = (Ruta) iterator.next();
            if (ruta.getId() == trayectoReserva) {
                rutaReserva = ruta;
            }
        }
        reserva.setRuta(rutaReserva);
                */
    }

}
