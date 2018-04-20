/**
 * @author: Gerson Morera Restrepo
 * @version: 17/04/2018
 * @Descripcion: Clase que representa la capa control en un MVC
 */
package control;

import java.io.File;
import java.util.ArrayList;
import modelo.GestionDocumento;

public class Control implements IControl {

    private static Control instance = null;

    protected Control() {
    }

    public static Control getInstance() {
        if (instance == null) {
            instance = new Control();
        }
        return instance;
    }

    /**
     * @Descripcion: metodo que me permite acceder a la capa modelo desde la
     * vista para contar lineas
     * @param file directorio en donde se encuentra el proyecto que se va a
     * contar.
     */
    @Override
    public void contarLineas(File file) {
        GestionDocumento.getInstance().setDocumentos(new ArrayList<>());
        GestionDocumento.getInstance().leerDocumentos(file);
    }

    /**
     * @Descripcion: metodo que me permite acceder a la capa modelo desde la
     * vista para contar lineas
     * @return devuelve el resultado del conteo a la pantalla desde el modelo
     */
    @Override
    public String imprimirRepuesta() {
        return (GestionDocumento.getInstance().imprimirRespuesta());
    }

}
