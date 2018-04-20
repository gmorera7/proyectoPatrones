/**
 * @author: Gerson Morera Restrepo
 * @version: 17/04/2018
 * @Descripcion: Interfaz de la capa control de un MVC
 */
package control;

import java.io.File;


public interface IControl {

    public void contarLineas(File file);

    public String imprimirRepuesta();

}
