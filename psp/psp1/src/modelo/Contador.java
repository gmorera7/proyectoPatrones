/**
 * @author: Gerson Morera Restrepo
 * @version: 17/04/2018
 * @Descripcion: clase que permite contar las lineas de codigo y metodos
 */
package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Contador implements IContador {

    private static Contador instance = null;

    protected Contador() {
    }

    public static Contador getInstance() {
        if (instance == null) {
            instance = new Contador();
        }
        return instance;
    }

    /**
     * @Descripcion: metodo del modelo que me permite contar lineas de codigo
     * @param file directorio en donde se encuentra el proyecto que se va a
     * contar.
     * @return devuelve la cantidad de lineas que se contaron en el directorio
     */
    @Override
    public int contarLineas(File file) {
        int contador = 0;
        try {
            Scanner input = new Scanner(file);

            while (input.hasNext()) {
                String linea = input.nextLine().trim();
                if (validarSiCuentaLinea(linea)) {
                    contador++;
                }
            }
            input.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contador;
    }

    /**
     * @Descripcion: metodo del modelo que me permite validar si la linea se
     * debe contar
     * @param linea linea del archivo que se va a validar
     * @return true en caso de que la linea sea valida para contar, false en
     * caso contrario
     */
    public boolean validarSiCuentaLinea(String linea) {
        if (linea != null && !linea.equalsIgnoreCase("")) {
            String caracter = linea.charAt(0) + "";
            if (!caracter.equalsIgnoreCase("")
                    && !caracter.equalsIgnoreCase(" ")
                    && !caracter.equalsIgnoreCase("/")
                    && !caracter.equalsIgnoreCase("}")
                    && !caracter.equalsIgnoreCase("*")) {
                return true;
            }
        }
        return false;
    }

    /**
     * @Descripcion: metodo del modelo que me permite contar los metodos
     * @param file archivo(.java) que se va a contar los metodos
     * @return entero correspondiente a la cantidad de lineas que conto
     */
    @Override
    public int contarMetodos(File file) {
        int contador = 0;
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String linea = input.nextLine();
                if (contarMetodo(linea)) {
                    contador++;
                }
            }
            input.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contador;
    }

    /**
     * @Descripcion: metodo del modelo que me permite validar cada linea si es
     * un metodo valido para contar
     * @param linea linea que se va a validar
     * @return retorna true si es una linea valida para contar y false en caso
     * contrario
     */
    public boolean contarMetodo(String linea) {
        boolean contarMetodo = false;
        if (linea != null) {
            int longitud = linea.trim().length();

            if (longitud >= 7) {
                String cadena = linea.trim().substring(0, 6);

                if (cadena.equalsIgnoreCase("public")) {
                    if (longitud >= 7 && longitud >= 12) {
                        cadena = linea.trim().substring(7, 12);
                        if (!cadena.equalsIgnoreCase("class") && !cadena.equalsIgnoreCase("inter")) {
                            contarMetodo = true;
                        }
                    }
                }
            }
        }
        return contarMetodo;
    }

}
