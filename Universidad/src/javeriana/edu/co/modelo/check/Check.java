package javeriana.edu.co.modelo.check;

/**
 *
 * @author javeriana.edu.co
 */
public abstract class Check {
    
    private Integer id;
    private String confirmacion;    

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the confirmacion
     */
    public String getConfirmacion() {
        return confirmacion;
    }

    /**
     * @param confirmacion the confirmacion to set
     */
    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }
}
