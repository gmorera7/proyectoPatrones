package edu.gpc.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anita
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByRolId", query = "SELECT u FROM Usuario u WHERE u.rolId = :rolId"),
    @NamedQuery(name = "Usuario.findByUsuario", query = "SELECT u FROM Usuario u WHERE u.usuario = :usuario"),
    @NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = :clave"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado"),
    @NamedQuery(name = "Usuario.findByUsuariocreacion", query = "SELECT u FROM Usuario u WHERE u.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Usuario.findByFechacreacion", query = "SELECT u FROM Usuario u WHERE u.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Usuario.findByUsuariomodificacion", query = "SELECT u FROM Usuario u WHERE u.usuariomodificacion = :usuariomodificacion"),
    @NamedQuery(name = "Usuario.findByFechamodificacion", query = "SELECT u FROM Usuario u WHERE u.fechamodificacion = :fechamodificacion")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

//    @Basic(optional = false)
    @NotNull
//    @Column(name = "rol_id")
    @JoinColumn(name = "rolid",referencedColumnName = "id")
    @ManyToOne
    private Rol rolId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "clave")
    private String clave;
    @Pattern(regexp="[A-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[A-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[A-Z0-9](?:[A-Z0-9-]*[A-Z0-9])?\\.)+[A-Z0-9](?:[A-Z0-9-]*[A-Z0-9])?", message="Email Invalido.")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
//    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "usuariocreacion")
    private String usuariocreacion;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Basic(optional = false)
//    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "usuariomodificacion")
    private String usuariomodificacion;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "fechamodificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, Rol rolId, String usuario, String clave, String email, String estado, String usuariocreacion, Date fechacreacion, String usuariomodificacion, Date fechamodificacion) {
        this.id = id;
        this.rolId = rolId;
        this.usuario = usuario;
        this.clave = clave;
        this.email = email;
        this.estado = estado;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
        this.usuariomodificacion = usuariomodificacion;
        this.fechamodificacion = fechamodificacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Rol getRolId() {
        return rolId;
    }

    public void setRolId(Rol rolId) {
        this.rolId = rolId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public String getUsuariocreacion() {
        return usuariocreacion;
    }

    public void setUsuariocreacion(String usuariocreacion) {
        this.usuariocreacion = usuariocreacion;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getUsuariomodificacion() {
        return usuariomodificacion;
    }

    public void setUsuariomodificacion(String usuariomodificacion) {
        this.usuariomodificacion = usuariomodificacion;
    }

    public Date getFechamodificacion() {
        return fechamodificacion;
    }

    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }
   
}
