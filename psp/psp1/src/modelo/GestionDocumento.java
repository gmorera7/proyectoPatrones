/**
 * @author: Gerson Morera Restrepo
 * @version: 17/04/2018
 * @Descripcion: clase modelo que me permite gestionar los documentos .java
 * leidos
 */
package modelo;

import java.io.File;
import java.util.ArrayList;

public class GestionDocumento implements IGestionDocumento {

    private ArrayList<Documento> documentos = new ArrayList<>();
    private static GestionDocumento instance = null;

    protected GestionDocumento() {
    }

    public static GestionDocumento getInstance() {
        if (instance == null) {
            instance = new GestionDocumento();
        }
        return instance;
    }

    /**
     * @Descripcion: metodo encargado de adicionar documentos al arraylist
     * @param documento documento que se encuentra en la ruta del directorio
     * para contar
     */
    @Override
    public void adicionarDocumentos(Documento documento) {
        getDocumentos().add(documento);
    }

    /**
     * @Descripcion: metodo encargado de validar si es una clase java
     * @param nombre nombre del directorio que se va a validar
     * @return devuelve true si el archivo es una clase .java, false en caso
     * contrario
     */
    public boolean esClaseJava(String nombre) {
        String extension = "";
        int i = nombre.lastIndexOf('.');
        if (i > 0) {
            extension = nombre.substring(i + 1);
        }
        if (extension.equalsIgnoreCase("java")) {
            return true;
        }
        return false;
    }

    /**
     * @Descripcion: metodo encargado de leer cada documento y validar si es un
     * file o un directorio, agrega el directorio al arreglo
     * @param file nombre del directorio o archivo que se va a validar
     */
    @Override
    public void leerDocumentos(File file) {

        Documento documento;
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.isDirectory()) {
                leerDocumentos(file1);
            } else {
                if (esClaseJava(file1.getName())) {
                    documento = new Documento();
                    documento.setNombre(file1.getName());
                    documento.setRuta(file1.getAbsolutePath());
                    adicionarDocumentos(documento);
                    documento.setNumeroLineas(Contador.getInstance().contarLineas(file1));
                    documento.setNumeroMetodos(Contador.getInstance().contarMetodos(file1));
                }
            }
        }
    }

    /**
     * @Descripcion: metodo encargado de armar la cadena de respuesta con el
     * resultado del conteo
     * @return cadena con respuesta del conteo
     */
    @Override
    public String imprimirRespuesta() {
        int contadorTotalLineas = 0;
        int contadorTotalMetodos = 0;
        String cadenaFinal = "Total de clases encontradas : " + documentos.size() + "\n";
        for (Documento documento : documentos) {
            cadenaFinal += " nombre de la clase:  " + documento.getNombre()
                    + " numero de lineas: " + documento.getNumeroLineas()
                    + " numero de metodos: " + documento.getNumeroMetodos() + "\n";
            contadorTotalLineas += documento.getNumeroLineas();
            contadorTotalMetodos += documento.getNumeroMetodos();
        }
        cadenaFinal += "Total de lineas : " + contadorTotalLineas + " Total de Metodos : " + contadorTotalMetodos;
        return cadenaFinal;
    }

    /**
     * @return the documentos
     */
    public ArrayList<Documento> getDocumentos() {
        return documentos;
    }

    /**
     * @param documentos the documentos to set
     */
    public void setDocumentos(ArrayList<Documento> documentos) {
        this.documentos = documentos;
    }

}
