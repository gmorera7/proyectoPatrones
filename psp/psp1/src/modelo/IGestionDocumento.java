/**
 * @author: Gerson Morera Restrepo
 * @version: 17/04/2018
 * @Descripcion: Interfaz de la clase GestionDocumento cumple la funcion de
 * template
 */
package modelo;

import java.io.File;

public interface IGestionDocumento {

    public void adicionarDocumentos(Documento documento);

    public void leerDocumentos(File file);

    public String imprimirRespuesta();

}
