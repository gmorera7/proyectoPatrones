package javeriana.edu.co.modelo.reserva;

import javeriana.edu.co.modelo.usuario.Pasajero;
import javeriana.edu.co.modelo.check.Check;
import javeriana.edu.co.modelo.comida.Comida;
import javeriana.edu.co.modelo.encuesta.Encuesta;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author javeriana.edu.co
 */
public class Reserva{

    private Integer id;
    private String numeroSilla;
    private Date fecha;
    private String estado;
    private Encuesta encuesta;
    private Comida comida;
    private Ruta ruta;
    private ArrayList<Check> check;
    private Pasajero persona;
    
    public Reserva(){
        check = new ArrayList<>();
    }

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
     * @return the numeroSilla
     */
    public String getNumeroSilla() {
        return numeroSilla;
    }

    /**
     * @param numeroSilla the numeroSilla to set
     */
    public void setNumeroSilla(String numeroSilla) {
        this.numeroSilla = numeroSilla;
    }

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
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the encuesta
     */
    public Encuesta getEncuesta() {
        return encuesta;
    }

    /**
     * @param encuesta the encuesta to set
     */
    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    /**
     * @return the comida
     */
    public Comida getComida() {
        return comida;
    }

    /**
     * @param comida the comida to set
     */
    public void setComida(Comida comida) {
        this.comida = comida;
    }

    /**
     * @return the ruta
     */
    public Ruta getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    /**
     * @return the check
     */
    public ArrayList<Check> getCheck() {
        return check;
    }

    /**
     * @param check the check to set
     */
    public void setCheck(ArrayList<Check> check) {
        this.check = check;
    }

    /**
     * @return the persona
     */
    public Pasajero getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Pasajero persona) {
        this.persona = persona;
    }

   

}
