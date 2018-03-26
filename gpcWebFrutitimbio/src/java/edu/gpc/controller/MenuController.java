package edu.gpc.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Rigo
 */
@ManagedBean(name="menuController")
@ViewScoped
public class MenuController {

    /**
     * Creates a new instance of MenuController
     */
    public MenuController() {
    }
    
    public void buttonAction(ActionEvent actionEvent) {
        System.out.println("Welcome to Primefaces!!");
    }
    
    public String rutaClientes (){
        System.out.println("Entro a ruta clientes...");
        String rClientes="listaclientes";
         System.out.println("retorno ruta:::: "+rClientes);
        return rClientes;       
    }
}
