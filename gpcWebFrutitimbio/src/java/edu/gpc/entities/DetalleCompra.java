package edu.gpc.entities;

import java.io.Serializable;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anita
 */
@Entity
@Table(name = "detalle_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCompra.findAll", query = "SELECT d FROM DetalleCompra d"),
    @NamedQuery(name = "DetalleCompra.findByIddetalleCompra", query = "SELECT d FROM DetalleCompra d WHERE d.iddetalleCompra = :iddetalleCompra"),
    @NamedQuery(name = "DetalleCompra.findByCantidad", query = "SELECT d FROM DetalleCompra d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DetalleCompra.findByPrecioUnitario", query = "SELECT d FROM DetalleCompra d WHERE d.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "DetalleCompra.findByTotal", query = "SELECT d FROM DetalleCompra d WHERE d.total = :total"),
    @NamedQuery(name = "DetalleCompra.findBySubtotal", query = "SELECT d FROM DetalleCompra d WHERE d.subtotal = :subtotal")})
public class DetalleCompra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalle_compra")
    private Integer iddetalleCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_unitario")
    private int precioUnitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private int total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subtotal")
    private int subtotal;
    @JoinColumn(name = "producto_codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Producto productoCodigo;
    @JoinColumn(name = "compra_codigo_compra", referencedColumnName = "codigo_compra")
    @ManyToOne(optional = false)
    private Compra compraCodigoCompra;
    // Transient
    @Transient
    private TipoPago tipoPago;
    

    public DetalleCompra() {
    }

    public DetalleCompra(Integer iddetalleCompra) {
        this.iddetalleCompra = iddetalleCompra;
    }

    public DetalleCompra(Integer iddetalleCompra, int cantidad, int precioUnitario, int total, int subtotal) {
        this.iddetalleCompra = iddetalleCompra;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
        this.subtotal = subtotal;
    }

    public Integer getIddetalleCompra() {
        return iddetalleCompra;
    }

    public void setIddetalleCompra(Integer iddetalleCompra) {
        this.iddetalleCompra = iddetalleCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(int precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public Producto getProductoCodigo() {
        return productoCodigo;
    }

    public void setProductoCodigo(Producto productoCodigo) {
        this.productoCodigo = productoCodigo;
    }

    public Compra getCompraCodigoCompra() {
        return compraCodigoCompra;
    }

    public void setCompraCodigoCompra(Compra compraCodigoCompra) {
        this.compraCodigoCompra = compraCodigoCompra;
    }
}
