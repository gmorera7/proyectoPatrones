/**
 * @author: Gerson Morera Restrepo
 * @version: 17/04/2018
 * @Descripcion: Interfaz de la clase Modelo Contador cumple la funcion de template
 */
package modelo;

import java.io.File;

public interface IContador {

    public int contarMetodos(File file);

    public int contarLineas(File file);

}
