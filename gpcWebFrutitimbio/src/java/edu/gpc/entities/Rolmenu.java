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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "rolmenu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rolmenu.findAll", query = "SELECT r FROM Rolmenu r"),
    @NamedQuery(name = "Rolmenu.findById", query = "SELECT r FROM Rolmenu r WHERE r.id = :id"),
    @NamedQuery(name = "Rolmenu.findByRolId", query = "SELECT r FROM Rolmenu r WHERE r.rolId = :rolId"),
    @NamedQuery(name = "Rolmenu.findByMenuId", query = "SELECT r FROM Rolmenu r WHERE r.menuId = :menuId")})
public class Rolmenu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rol_id")
    private int rolId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "menu_id")
    private int menuId;

    public Rolmenu() {
    }

    public Rolmenu(Integer id) {
        this.id = id;
    }

    public Rolmenu(Integer id, int rolId, int menuId) {
        this.id = id;
        this.rolId = rolId;
        this.menuId = menuId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rolmenu)) {
            return false;
        }
        Rolmenu other = (Rolmenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "control.Rolmenu[ id=" + id + " ]";
    }
    
}
