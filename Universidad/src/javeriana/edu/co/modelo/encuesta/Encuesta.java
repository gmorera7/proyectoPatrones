package javeriana.edu.co.modelo.encuesta;

import java.util.Date;

/**
 *
 * @author javeriana.edu.co
 */
public class Encuesta {

    private Date fecha;
    private String calificacion;
    private Integer idReserva;

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the calificacion
     */
    public String getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the idReserva
     */
    public Integer getIdReserva() {
        return idReserva;
    }

    /**
     * @param idReserva the idReserva to set
     */
    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

}
