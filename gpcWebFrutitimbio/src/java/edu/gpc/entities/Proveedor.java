package edu.gpc.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RIGO
 */
@Entity
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByCodigo", query = "SELECT p FROM Proveedor p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Proveedor.findByTipoIdentificacion", query = "SELECT p FROM Proveedor p WHERE p.tipoIdentificacion = :tipoIdentificacion"),
    @NamedQuery(name = "Proveedor.findByNumeroIdentificacion", query = "SELECT p FROM Proveedor p WHERE p.numeroIdentificacion = :numeroIdentificacion"),
    @NamedQuery(name = "Proveedor.findByNombre1", query = "SELECT p FROM Proveedor p WHERE p.nombre1 = :nombre1"),
    @NamedQuery(name = "Proveedor.findByNombre2", query = "SELECT p FROM Proveedor p WHERE p.nombre2 = :nombre2"),
    @NamedQuery(name = "Proveedor.findByApellido1", query = "SELECT p FROM Proveedor p WHERE p.apellido1 = :apellido1"),
    @NamedQuery(name = "Proveedor.findByApellido2", query = "SELECT p FROM Proveedor p WHERE p.apellido2 = :apellido2"),
    @NamedQuery(name = "Proveedor.findByDireccion", query = "SELECT p FROM Proveedor p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Proveedor.findByTipoTelefono", query = "SELECT p FROM Proveedor p WHERE p.tipoTelefono = :tipoTelefono"),
    @NamedQuery(name = "Proveedor.findByNumeroTelefono", query = "SELECT p FROM Proveedor p WHERE p.numeroTelefono = :numeroTelefono"),
    @NamedQuery(name = "Proveedor.findByEmail", query = "SELECT p FROM Proveedor p WHERE p.email = :email"),
    @NamedQuery(name = "Proveedor.findByEstado", query = "SELECT p FROM Proveedor p WHERE p.estado = :estado")})

public class Proveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)   
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "tipo_identificacion")
    private String tipoIdentificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numero_identificacion")
    private String numeroIdentificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre1")
    private String nombre1;
    @Basic(optional = false)   
    
    @Column(name = "nombre2")
    private String nombre2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "apellido1")
    private String apellido1;
    
    @Basic(optional = false)    
    @Column(name = "apellido2")
    private String apellido2;

    @Column(name = "departamento")
    private String departamento;
   
    @NotNull
    @JoinColumn(name = "municipio_idmunicipio",referencedColumnName = "idmunicipio")
    @ManyToOne
    private Municipio municipio;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
   
//    @Size(min = 1, max = 10)
    @Column(name = "tipo_telefono")
    private String tipoTelefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numero_telefono")
    private String numeroTelefono;
   
//    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    
    @NotNull
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "proveedorCodigo")
    private List<Compra> compraList;

    public Proveedor() {
    }

    public Proveedor(Integer codigo) {
        this.codigo = codigo;
    }

    public Proveedor(Integer codigo, String tipoIdentificacion, String numeroIdentificacion, String nombre1, String nombre2, String apellido1, String apellido2, String departamento, Municipio municipio, String direccion, String tipoTelefono, String numeroTelefono, String estado) {
        this.codigo = codigo;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.departamento = departamento;
        this.municipio = municipio;
        this.direccion = direccion;
        this.tipoTelefono = tipoTelefono;
        this.numeroTelefono = numeroTelefono;
        this.estado = estado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoTelefono() {
        return tipoTelefono;
    }

    public void setTipoTelefono(String tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }   
}
