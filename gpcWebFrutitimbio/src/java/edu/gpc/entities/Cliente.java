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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rigo
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByNit", query = "SELECT c FROM Cliente c WHERE c.nit = :nit"),
    @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cliente.findByRepresentanteLegal", query = "SELECT c FROM Cliente c WHERE c.representanteLegal = :representanteLegal"),
//    @NamedQuery(name = "Cliente.findByDepartamento", query = "SELECT c FROM Cliente c WHERE c.departamento = :departamento"),
//    @NamedQuery(name = "Cliente.findByMunicipio", query = "SELECT c FROM Cliente c WHERE c.municipio = :municipio"),
    @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Cliente.findByTipoTelefono", query = "SELECT c FROM Cliente c WHERE c.tipoTelefono = :tipoTelefono"),
    @NamedQuery(name = "Cliente.findByNumeroTelefono", query = "SELECT c FROM Cliente c WHERE c.numeroTelefono = :numeroTelefono"),
    @NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email"),
    @NamedQuery(name = "Cliente.findByEstado", query = "SELECT c FROM Cliente c WHERE c.estado = :estado")})

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "nit", updatable = false)
    private Integer nit;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "representante_legal")
    private String representanteLegal;

    @Basic(optional = false)
    @Size(min = 1, max = 30)
    @Column(name = "departamento")
    private String departamento;

    @NotNull
    @JoinColumn(name = "municipio_idmunicipio", referencedColumnName = "idmunicipio")
    @ManyToOne
    private Municipio municipio;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "direccion")
    private String direccion;
    
    @Basic(optional = false)
    @Size(min = 1, max = 10)
    @Column(name = "tipo_telefono")
    private String tipoTelefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numero_telefono")
    private String numeroTelefono;
//    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteNit")
    private List<Venta> ventaList;

    public Cliente() {
    }

    public Cliente(Integer nit) {
        this.nit = nit;
    }

    public Cliente(Integer nit, String nombre, String representanteLegal, String departamento, Municipio municipio, String direccion, String tipoTelefono, String numeroTelefono, String email, String estado) {
        this.nit = nit;
        this.nombre = nombre;
        this.representanteLegal = representanteLegal;
        this.departamento = departamento;
        this.municipio = municipio;
        this.direccion = direccion;
        this.tipoTelefono = tipoTelefono;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
        this.estado = estado;
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
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

    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }
}