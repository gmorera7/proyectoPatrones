package javeriana.edu.co.calculardesviacion.vista;

import java.util.Scanner;
import javeriana.edu.co.calculardesviacion.control.ControlVista;
import javeriana.edu.co.calculardesviacion.control.IControlVista;

/**
 *
 * @author gersonmorera
 */
public class Vista {

    private IControlVista controlVista = ControlVista.getInstance();

    private static Vista instance = null;

    protected Vista() {
    }

    public static Vista getInstance() {
        if (instance == null) {
            instance = new Vista();
        }
        return instance;
    }

    public void solicitarYCalcularDesviacion() {

        System.err.println("Por favor la cantidad de numeros : ");
        Scanner entradaEscaner = new Scanner(System.in); //Creación de un objeto Scanner
        int cantidadNumeros = entradaEscaner.nextInt();

        for (int contador = 0; contador < cantidadNumeros; contador++) {
            System.err.println("ingresar el numero " + contador + " :");
            entradaEscaner = new Scanner(System.in); //Creación de un objeto Scanner
            int numero = entradaEscaner.nextInt();
            controlVista.adicionarElementoMemoria(numero);
        }
        Double media = controlVista.calcularMediaAritmetica();
        Double desviacion = controlVista.calcularDesviacionEstandar(media);
        System.err.println("Media aritmetica : " + media);
        System.err.println("Desviacion estandar : " + desviacion);

    }

}
