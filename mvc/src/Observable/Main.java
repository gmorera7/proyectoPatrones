/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Observable;

/**
 *
 * @author Administrador
 */
public class Main {
    
    public static void main(String[] args) {
        Libro libro =  new Libro();
        libro.setTitulo("hola");
        libro.setEstado("MALO");
        Biblioteca b = new Biblioteca();
        b.devolverLibro(libro);
    }
    
}
