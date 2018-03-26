package edu.gpc.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anita
 */
@Entity
@Table(name = "departamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamentos.findAll", query = "SELECT d FROM Departamentos d"),
    @NamedQuery(name = "Departamentos.findByIddepartamento", query = "SELECT d FROM Departamentos d WHERE d.iddepartamento = :iddepartamento"),
    @NamedQuery(name = "Departamentos.findByCoddepto", query = "SELECT d FROM Departamentos d WHERE d.coddepto = :coddepto"),
    @NamedQuery(name = "Departamentos.findByDescripcion", query = "SELECT d FROM Departamentos d WHERE d.descripcion = :descripcion")})
public class Departamentos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddepartamento")
    private Integer iddepartamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "coddepto")
    private String coddepto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "descripcion")
    private String descripcion;

    public Departamentos() {
    }

    public Departamentos(Integer iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public Departamentos(Integer iddepartamento, String coddepto, String descripcion) {
        this.iddepartamento = iddepartamento;
        this.coddepto = coddepto;
        this.descripcion = descripcion;
    }

    public Integer getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Integer iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public String getCoddepto() {
        return coddepto;
    }

    public void setCoddepto(String coddepto) {
        this.coddepto = coddepto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (iddepartamento != null ? iddepartamento.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Departamentos)) {
//            return false;
//        }
//        Departamentos other = (Departamentos) object;
//        if ((this.iddepartamento == null && other.iddepartamento != null) || (this.iddepartamento != null && !this.iddepartamento.equals(other.iddepartamento))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "controller.Departamentos[ iddepartamento=" + iddepartamento + " ]";
//    }
    
}
