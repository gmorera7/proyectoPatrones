/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javeriana.edu.co.reportes;

import java.util.Date;

/**
 *
 * @author gerson
 */
public class ReporteAbastecedor {
    
    private String numeroVuelo;
    private Date fechaVuelo;
    private String calificacion;

    /**
     * @return the numeroVuelo
     */
    public String getNumeroVuelo() {
        return numeroVuelo;
    }

    /**
     * @param numeroVuelo the numeroVuelo to set
     */
    public void setNumeroVuelo(String numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    /**
     * @return the fechaVuelo
     */
    public Date getFechaVuelo() {
        return fechaVuelo;
    }

    /**
     * @param fechaVuelo the fechaVuelo to set
     */
    public void setFechaVuelo(Date fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
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
    
}
