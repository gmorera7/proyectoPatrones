package edu.gpc.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Anita
 */
@Entity
@Table(name = "tipo_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPago.findAll", query = "SELECT t FROM TipoPago t"),
    @NamedQuery(name = "TipoPago.findByIdtipoPago", query = "SELECT t FROM TipoPago t WHERE t.idtipoPago = :idtipoPago"),
    @NamedQuery(name = "TipoPago.findByDestipoPago", query = "SELECT t FROM TipoPago t WHERE t.destipoPago = :destipoPago")})
public class TipoPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipo_pago")
    private Integer idtipoPago;
    @Size(max = 50)
    @Column(name = "destipo_pago")
    private String destipoPago;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoPagoIdtipoPago")
    private List<Compra> compraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoPagoIdtipoPago")
    private List<Venta> ventaList;
    @Column(name = "codigo")
    private String codigo;

    public TipoPago() {
    }

    public TipoPago(Integer idtipoPago) {
        this.idtipoPago = idtipoPago;
    }

    public Integer getIdtipoPago() {
        return idtipoPago;
    }

    public void setIdtipoPago(Integer idtipoPago) {
        this.idtipoPago = idtipoPago;
    }

    public String getDestipoPago() {
        return destipoPago;
    }

    public void setDestipoPago(String destipoPago) {
        this.destipoPago = destipoPago;
    }

    @XmlTransient
    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }

    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
