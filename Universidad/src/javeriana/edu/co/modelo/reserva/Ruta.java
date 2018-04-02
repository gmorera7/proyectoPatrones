package javeriana.edu.co.modelo.reserva;

import java.util.Date;

/**
 *
 * @author javeriana.edu.co
 */
public class Ruta {

    private Integer id;
    private String destino;
    private String origen;
    private String duracionVuelo;
    private Long precio;
    private Date fechaSalida;
    private Date fechaLlegada;
    private String noVuelo;

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
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return the origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * @return the duracionVuelo
     */
    public String getDuracionVuelo() {
        return duracionVuelo;
    }

    /**
     * @param duracionVuelo the duracionVuelo to set
     */
    public void setDuracionVuelo(String duracionVuelo) {
        this.duracionVuelo = duracionVuelo;
    }

    /**
     * @return the precio
     */
    public Long getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    /**
     * @return the noVuelo
     */
    public String getNoVuelo() {
        return noVuelo;
    }

    /**
     * @param noVuelo the noVuelo to set
     */
    public void setNoVuelo(String noVuelo) {
        this.noVuelo = noVuelo;
    }

    /**
     * @return the fechaSalida
     */
    public Date getFechaSalida() {
        return fechaSalida;
    }

    /**
     * @param fechaSalida the fechaSalida to set
     */
    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    /**
     * @return the fechaLlegada
     */
    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    /**
     * @param fechaLlegada the fechaLlegada to set
     */
    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

}
