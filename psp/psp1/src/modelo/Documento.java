/**
 * @author: Gerson Morera Restrepo
 * @version: 17/04/2018
 * @Descripcion: clase modelo para representar los documentos java que se leen
 */
package modelo;

public class Documento {

    private String ruta;
    private int numeroLineas;
    private String nombre;
    private int numeroMetodos;

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * @return the numeroLineas
     */
    public int getNumeroLineas() {
        return numeroLineas;
    }

    /**
     * @param numeroLineas the numeroLineas to set
     */
    public void setNumeroLineas(int numeroLineas) {
        this.numeroLineas = numeroLineas;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the numeroMetodos
     */
    public int getNumeroMetodos() {
        return numeroMetodos;
    }

    /**
     * @param numeroMetodos the numeroMetodos to set
     */
    public void setNumeroMetodos(int numeroMetodos) {
        this.numeroMetodos = numeroMetodos;
    }

}
