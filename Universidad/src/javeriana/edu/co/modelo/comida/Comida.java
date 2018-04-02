package javeriana.edu.co.modelo.comida;
/**
 *
 * @author javeriana.edu.co
 */
public abstract class Comida {

    private String descripcion;

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
